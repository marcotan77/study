package com.xxx.bizlog.aspect;

import com.alibaba.fastjson.JSON;
import com.xxx.bizlog.annotation.BizLog;
import com.xxx.bizlog.context.LogContext;
import com.xxx.bizlog.handler.LogDiffHandler;
import com.xxx.bizlog.jdbc.JdbcUtils;
import com.xxx.bizlog.jdbc.PrimaryParam;
import com.xxx.bizlog.jdbc.QueryWrapper;
import com.xxx.bizlog.reflect.DiffPropertyDefinition;
import com.xxx.bizlog.reflect.ObjectCompareUtils;
import com.xxx.bizlog.registry.LogDefinitionHolder;
import com.xxx.bizlog.tx.AsyncAfterCommitExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@Component
@Slf4j
@Aspect
public class LogAspect implements Ordered {

    /**
     * 用于SpEL表达式解析.
     */
    private SpelExpressionParser parser = new SpelExpressionParser();

    /**
     * 用于获取方法参数定义名字.
     */
    private ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
    @Resource
    private AsyncAfterCommitExecutor asyncAfterCommitExecutor;
    @Autowired
    private JdbcUtils jdbcUtils;

    @Autowired
    private List<LogDiffHandler> logDiffHandlers;

    @Around("@annotation(com.zto56.bizlog.annotation.BizLog)")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        try {
            QueryWrapper queryWrapper = buildQueryWrapper(pjp);
            Object before = jdbcUtils.getByPrimaryKey(queryWrapper);
            Object proceed = pjp.proceed();
            Object after = jdbcUtils.getByPrimaryKey(queryWrapper);
            //获取更新人
            queryWrapper.setModifiedBy(getUpdater(after, pjp.getArgs()));
            try {
                asyncAfterCommitExecutor.execute(() -> compare(queryWrapper, before, after));
            } catch (Exception e) {
                log.error("操作日志处理失败", e);
            }
            return proceed;
        } finally {
            LogContext.clear();
        }
    }

    /**
     * 对比
     *
     * @param before
     * @param after
     */
    private void compare(QueryWrapper queryWrapper, Object before, Object after) {
        List<DiffPropertyDefinition> differentProperty = ObjectCompareUtils.getDifferentProperty(before, after);
        if (CollectionUtils.isEmpty(differentProperty)) {
            log.info("没有修改项，table = {}, module = {}", queryWrapper.getTable(), queryWrapper.getModule());
            return;
        }
        if (CollectionUtils.isNotEmpty(logDiffHandlers)) {
            logDiffHandlers.forEach(logDiffHandler -> logDiffHandler.handler(queryWrapper, differentProperty));
        }

    }


    /**
     * 获取更新人
     *
     * @param after
     * @return
     */
    private Long getUpdater(Object after, Object[] args) {
        if (after != null) {
            Field updater = LogDefinitionHolder.getUpdater(after.getClass());
            if (updater != null) {
                Long updaterValue = getUpdaterValue(after, updater);
                if (updaterValue != null) return updaterValue;
            }
        }
        for (Object arg : args) {
            if (arg != null && !arg.getClass().isPrimitive()) {
                Field field = LogDefinitionHolder.forClassUpdater(arg.getClass());
                Long updaterValue = getUpdaterValue(arg, field);
                if (updaterValue != null) return updaterValue;
            }
        }
        return -1L;
    }

    private Long getUpdaterValue(Object after, Field updater) {
        try {
            updater.setAccessible(true);
            Object updaterValue = updater.get(after);
            if (updaterValue instanceof Number) {
                return Long.valueOf(updaterValue.toString());
            }
            if (updaterValue instanceof String) {
                return Long.valueOf(updaterValue.toString());
            }
        } catch (IllegalAccessException | NumberFormatException e) {
            //ignore
            log.error("获取更新人失败,after = {}", JSON.toJSONString(after), e);
        }
        return null;
    }


    /**
     * 构建查询包装
     *
     * @param pjp
     * @return
     */
    private QueryWrapper buildQueryWrapper(ProceedingJoinPoint pjp) {
        MethodSignature sign = (MethodSignature) pjp.getSignature();
        Method method = sign.getMethod();
        BizLog bizLog = method.getAnnotation(BizLog.class);
        Object primaryValue = generateKeyBySpEL(bizLog.value(), pjp);
        String optSequenceNumber = LogContext.getSequenceNumber();
        if (StringUtils.isEmpty(optSequenceNumber)) {
            optSequenceNumber = generateKeyBySpEL(bizLog.optSequenceNumber(), pjp).toString();
        }
        return buildQueryWrapper(bizLog, primaryValue, optSequenceNumber);
    }


    /**
     * 构建查询包装
     *
     * @param bizLog
     * @param primaryValue
     * @return
     */
    private QueryWrapper buildQueryWrapper(BizLog bizLog, Object primaryValue, String optSequenceNumber) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Class<?> poClazz = bizLog.poClazz();
        Field primaryKey = LogDefinitionHolder.getPrimaryKey(poClazz);
        String table = LogDefinitionHolder.getTable(poClazz);
        queryWrapper.setTable(table);
        queryWrapper.setReturnClazz(poClazz);
        queryWrapper.setModule(bizLog.module());
        PrimaryParam primaryParam = new PrimaryParam(primaryKey, primaryValue);
        queryWrapper.setPrimaryParam(primaryParam);
        queryWrapper.setOptSequenceNumber(optSequenceNumber);
        return queryWrapper;
    }

    /**
     * 解析spel表达式
     *
     * @param spELString
     * @param joinPoint
     * @return
     */
    public Object generateKeyBySpEL(String spELString, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        if (paramNames == null || paramNames.length == 0) {
            throw new RuntimeException("方法参数不能为空,请检查 , " + methodSignature.getMethod().getName());
        }
        Expression expression = parser.parseExpression(spELString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
