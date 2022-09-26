package com.example.springboot.aspect;

import com.example.springboot.mapper.SysUserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tanchusheng
 * @version 1.0
 * @description: 变更日志切面
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


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Object proceed = point.proceed();
        // 切入点所在目标对象
        Object target = point.getTarget();
        // 获取方法的参数
        Object[] args = point.getArgs();

        // 获取切入点方法的名字
        String methodName = point.getSignature().getName().toLowerCase();

        Method[] declaredMethods = target.getClass().getDeclaredMethods();

        String objName = args[0].getClass().toString();

        String backupObjectName = objName.substring(objName.indexOf(" ") + 1);

        String[] backupObjNameSections = backupObjectName.split("\\.");

        // 目标对象名
        String backupNameSpace = backupObjNameSections[backupObjNameSections.length - 1];

        if (args[0] instanceof List) {
            // System.out.println("batch   operator  list");
            for (Object obj : (List) args[0]) {
                String tempBackupNameSpace = obj.getClass().toString().substring(objName.indexOf(" ") + 1);

                String[] tempBackupObjNameSections = tempBackupNameSpace.split("\\.");
                backupNameSpace = tempBackupObjNameSections[tempBackupObjNameSections.length - 1];

                backUpToLog(backupNameSpace, methodName, obj, point);
            }
        } else {
            backUpToLog(backupNameSpace, methodName, args[0], point);
        }

        return null;
    }

    /**
     *
     * @param backupNameSpace 目标类名
     * @param methodName 方法名
     * @param args 参数
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void backUpToLog(String backupNameSpace, String methodName, Object args,ProceedingJoinPoint point) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        if (backUpList.contains(backupNameSpace)) {
            if (methodName.indexOf("delete") != -1 || methodName.indexOf("update") != -1) {
                /**操作类型  1：修改  2 ：删除**/
                Integer operatorType = 1;
                if (methodName.indexOf("delete") != -1)
                    operatorType = 2;
                Class DOClass = args.getClass();
                Method[] methods = args.getClass().getDeclaredMethods();

                Method getId = null;
                Method setSiteId = null;
                Method getSiteCode = null;
                for (Method tmpMethod : methods) {
                    if (tmpMethod.getName().indexOf("getId") != -1) {
                        getId = tmpMethod;
                        break;
                    }
                }

                String primaryKeyId = null;
                Object paramObj = DOClass.newInstance();


                Method[] allMethods = DOClass.getDeclaredMethods();
                String backupSelectId = backupNameSpace + ".selectByPrimaryKey";
                SqlSession sqlSession = sqlSessionFactory.openSession();
//                sqlSession.selectOne(paramObj.getId);
                // 根据ID查询对象信息
//
                MethodSignature signature = (MethodSignature) point.getSignature();
                Class<?> aClass = signature.getMethod().getDeclaringClass();
                Method[] methods1 = aClass.getMethods();
                List<Method> selectByPrimaryKey = Arrays.stream(methods1).filter(item -> item.getName().indexOf("selectByPrimaryKey") != -1).collect(Collectors.toList());
                Method method = selectByPrimaryKey.get(0);
                Object selectByPrimaryKey1 = method.invoke("selectByPrimaryKey", 1);
                Mapper mapper = (Mapper) sqlSession.getMapper(aClass);
//                MapperProxyFactory mapperProxyFactory = new MapperProxyFactory()
                // public abstract int com.example.springboot.mapper.SysUserMapper.updateByPrimaryKeySelective(com.example.springboot.domain.SysUser)
                Object backupObj = ((SysUserMapper)sqlSession.getMapper(aClass)).selectByPrimaryKey(1L);
//                ((SysUserMapper)sqlSession.getMapper(((MethodSignature)point.getSignature()).getMethod().clazz)).selectByPrimaryKey(1L)
//                Object backupObj = this.queryForObject(backupSelectId, paramObj);
//                Object backupObj = new Object();


                List<Method> originalMethod = filterField(allMethods);
                List<Method> modifyMethod = filterField(args.getClass().getDeclaredMethods());

                for (int i = 0; i < originalMethod.size(); i++) {

                    Method original = originalMethod.get(i);
                    Method modify = modifyMethod.get(i);

                    String fieldName = original.getName().substring(3, original.getName().length()).toUpperCase();

                    String originalValue = "";
                    String modifyValue = "";

                    Object originalObj = original.invoke(backupObj);
                    Object modifyObj = modify.invoke(args);
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


                        // 保存到数据库
                        System.out.println(original.getName() + "  " + originalValue + "   ======>   " + modifyValue);
                    }
                }

                sqlSession.close();
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
            if (methodName.indexOf("get") != -1) {
                if ((methodName.indexOf("getCreatedBy") != -1)
                        || (methodName.indexOf("getCreatedTime") != -1)
                        || (methodName.indexOf("getModifiedBy") != -1)
                        || (methodName.indexOf("getModifiedTime") != -1)) {
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
