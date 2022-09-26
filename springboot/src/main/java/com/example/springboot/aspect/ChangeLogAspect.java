package com.example.springboot.aspect;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanchusheng
 * @version 1.0
 * @description: 前置通知 变更日志切面
 * @date: 2022/9/26 13:14
 */

@Aspect
@Component
@Order(-1)
public class ChangeLogAspect {
    private static List<String> backUpList = new ArrayList<String>();

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    static {
        backUpList.add("SysUser");
    }

    @Pointcut("execution(* com.example.springboot.mapper.SysUserMapper.update*(..))")
    public void pointCut() {
    }

    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容。
     * value：绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     * <br/>
     * * @param joinPoint：提供对连接点处可用状态和有关它的静态信息的反射访问<br/> <p>
     * * * Object[] getArgs()：返回此连接点处（目标方法）的参数，目标方法无参数时，返回空数组
     * * * Signature getSignature()：返回连接点处的签名。
     * * * Object getTarget()：返回目标对象
     * * * Object getThis()：返回当前正在执行的对象
     * * * StaticPart getStaticPart()：返回一个封装此连接点的静态部分的对象。
     * * * SourceLocation getSourceLocation()：返回与连接点对应的源位置
     * * * String toLongString()：返回连接点的扩展字符串表示形式。
     * * * String toShortString()：返回连接点的缩写字符串表示形式。
     * * * String getKind()：返回表示连接点类型的字符串
     * * * </p>
     */
    @Before("pointCut()")
    public Object around(JoinPoint point) throws Throwable {

        // 切入点所在目标对象
        Object target = point.getTarget();
        // 获取方法的参数
        Object[] args = point.getArgs();

        // 获取切入点方法的名字
        String methodName = point.getSignature().getName().toLowerCase();

        String objName = args[0].getClass().toString();

        String backupObjectName = objName.substring(objName.indexOf(" ") + 1);

        String[] backupObjNameSections = backupObjectName.split("\\.");

        // 目标对象名
        String backupNameSpace = backupObjNameSections[backupObjNameSections.length - 1];

        MethodSignature signature = (MethodSignature) point.getSignature();

        if (args[0] instanceof List) {
            // System.out.println("batch   operator  list");
            for (Object obj : (List) args[0]) {
                String tempBackupNameSpace = obj.getClass().toString().substring(objName.indexOf(" ") + 1);

                String[] tempBackupObjNameSections = tempBackupNameSpace.split("\\.");
                backupNameSpace = tempBackupObjNameSections[tempBackupObjNameSections.length - 1];

                backUpToLog(backupNameSpace, methodName, obj, target, signature);
            }
        } else {
            backUpToLog(backupNameSpace, methodName, args[0], target, signature);
        }

        return null;
    }

    /**
     * @param backupNameSpace 目标类名
     * @param methodName      方法名
     * @param args            参数
     * @param target          目标对象
     * @param signature       方法签名
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void backUpToLog(String backupNameSpace, String methodName, Object args, Object target, MethodSignature signature) throws IllegalAccessException, InvocationTargetException {
        if (backUpList.contains(backupNameSpace)) {
            if (methodName.contains("delete") || methodName.contains("update")) {
                /**操作类型  1：修改  2 ：删除**/
                Integer operatorType = 1;
                if (methodName.contains("delete"))
                    operatorType = 2;

                // 参数class
                Class<?> argsClass = args.getClass();
                Method[] argsMethods = argsClass.getDeclaredMethods();

                // 目标class
                Class<?> targetClass = target.getClass();
                Method[] targetMethod = targetClass.getDeclaredMethods();

                Class<?> aClass = signature.getMethod().getDeclaringClass();
                Method[] methods1 = aClass.getMethods();

                // 过滤出selectByPrimaryKey方法
                List<Method> methodList = Arrays.stream(methods1).filter(item -> item.getName().contains("selectByPrimaryKey")).collect(Collectors.toList());
                if (methodList.size() == 0) {
                    return;
                }
                SqlSession sqlSession = sqlSessionFactory.openSession();
                MapperMethod mapperMethod = new MapperMethod(target.getClass(), methodList.get(0), sqlSession.getConfiguration());
                Object[] param = {args};
                // 获取原来的对象
                Object backupObj = mapperMethod.execute(sqlSession, param);

                List<Method> originalMethod = filterField(argsMethods);

                for (int i = 0; i < originalMethod.size(); i++) {

                    Method method = originalMethod.get(i);

                    String fieldName = method.getName().substring(3).toUpperCase();

                    String originalValue = "";
                    String modifyValue = "";

                    Object originalObj = method.invoke(backupObj);
                    Object modifyObj = method.invoke(args);

                    // TODO 对不同的字段类型进行赋值
//                    originalValue = busiLogService.getFieldValue(fieldName, originalValue);
//                    modifyValue = busiLogService.getFieldValue(fieldName, modifyValue);
                    if (originalObj != null && originalObj.toString().trim().length() > 0)
                        originalValue = originalObj.toString();
                    if (modifyObj != null && modifyObj.toString().trim().length() > 0)
                        modifyValue = modifyObj.toString();
                    if (originalValue == null && modifyValue == null)
                        continue;

                    if ((originalValue != null && modifyValue != null && (!originalValue.equals(modifyValue)))
                            || (originalValue == null && modifyValue != null)
                            || (originalValue != null && modifyValue == null)) {
                        // TODO 保存到数据库
                        System.out.println(fieldName + ": " + originalValue + "   ======>   " + modifyValue);
                    }
                }
            }


        }
    }

    /**
     * 过滤掉创建修改字段
     *
     * @param allMethods
     * @return
     */
    public static List<Method> filterField(Method[] allMethods) {
        List<Method> listMethod = new ArrayList<Method>();
        for (Method tempMethod : allMethods) {
            String methodName = tempMethod.getName();
            if (methodName.contains("get")) {
                if ((methodName.contains("getCreatedBy"))
                        || (methodName.contains("getCreatedTime"))
                        || (methodName.contains("getModifiedBy"))
                        || (methodName.contains("getModifiedTime"))) {
                    continue;
                }
                Class[] parameterTypes = tempMethod.getParameterTypes();
                if (parameterTypes.length == 0) {
                    listMethod.add(tempMethod);
                }
            }
        }
        return listMethod;
    }
}
