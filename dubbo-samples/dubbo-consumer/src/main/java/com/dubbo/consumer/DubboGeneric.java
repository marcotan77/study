package com.dubbo.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cfne.cuckoo.framework.web.context.IRequestArgumentBody;
import com.cfne.cuckoo.framework.web.context.RequestArgumentBody;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * dubbo 泛化调用
 * @author tanchusheng
 * @date 2023/6/30
 */
public class DubboGeneric {

    private static GenericService genericService;

    public static void main(String[] args) throws InterruptedException {

        //创建ApplicationConfig
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-call-consumer");
        //创建注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("nacos://10.202.16.163:8848");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("namespace","b48b2798-7639-44dc-a972-4b470dfae319");
        parameters.put("username","nacos");
        parameters.put("password","uat_nacos221");
        registryConfig.setParameters(parameters);

        //创建服务引用配置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        //设置接口
        referenceConfig.setInterface("com.ztozy.ctms.vas.client.insurance.api.InsuranceFacade");
        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        //重点：设置为泛化调用
        //注：不再推荐使用参数为布尔值的setGeneric函数
        //应该使用referenceConfig.setGeneric("true")代替
        referenceConfig.setGeneric(true);
        //设置异步，不必须，根据业务而定。
        referenceConfig.setAsync(true);
        //设置超时时间
        referenceConfig.setTimeout(7000);

        IRequestArgumentBody argument = new RequestArgumentBody(getParameter());


        //获取服务，由于是泛化调用，所以获取的一定是GenericService类型
        genericService = referenceConfig.get();

        //使用GenericService类对象的$invoke方法可以代替原方法使用
        //第一个参数是需要调用的方法名
        //第二个参数是需要调用的方法的参数类型数组，为String数组，里面存入参数的全类名。
        //第三个参数是需要调用的方法的参数数组，为Object数组，里面存入需要的参数。
        Object result = genericService.$invoke("exportInsurance",
                new String[]{IRequestArgumentBody.class.getName()},
                new Object[]{getParameter()});

        System.out.println(JSON.toJSONString(result));
        //使用CountDownLatch，如果使用同步调用则不需要这么做。
//        CountDownLatch latch = new CountDownLatch(1);
//        //获取结果
//        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
//        future.whenComplete((value, t) -> {
//            System.err.println("invokeSayHello(whenComplete): " + value);
//            latch.countDown();
//        });
//        //打印结果
//        System.err.println("invokeSayHello(return): " + result);
//        latch.await();
    }

    private static JSONObject getParameter(){

        String paramStr = "{\n" +
                "  \"userInfo\": {\n" +
                "    \"curOrgCode\": \"G1402\",\n" +
                "    \"admin\": false,\n" +
                "    \"employeeNo\": \"00039643\",\n" +
                "    \"curCompanyName\": \"中通智运数字科技有限公司\",\n" +
                "    \"curDeptId\": 294,\n" +
                "    \"curDeptName\": \"公共服务组\",\n" +
                "    \"postName\": \"后端开发工程师\",\n" +
                "    \"curOrgId\": 294,\n" +
                "    \"id\": 453358,\n" +
                "    \"mobile\": \"15575219234\",\n" +
                "    \"curCompanyType\": 0,\n" +
                "    \"employeeId\": 644,\n" +
                "    \"userName\": \"00039643\",\n" +
                "    \"curDeptCode\": \"G1402\",\n" +
                "    \"curCompanyId\": 8,\n" +
                "    \"realName\": \"谭楮升\",\n" +
                "    \"curOrgName\": \"公共服务组\",\n" +
                "    \"tenantId\": 8,\n" +
                "    \"curCompanyCode\": \"Z0506\"\n" +
                "  },\n" +
                "  \"data\": {\n" +
                "    \"companyCode\": \"\",\n" +
                "    \"proCode\": \"\",\n" +
                "    \"endDate\": \"2023-06-15 23:59:59\",\n" +
                "    \"dataType\": \"order_no\",\n" +
                "    \"timeType\": \"stowage_time\",\n" +
                "    \"dataBizCode\": \"\",\n" +
                "    \"feeType\": \"\",\n" +
                "    \"beginDate\": \"2023-05-16 00:00:00\",\n" +
                "    \"sendCustCode\": \"\",\n" +
                "    \"driverId\": \"\",\n" +
                "    \"carNo\": \"\",\n" +
                "    \"carrierCode\": \"\",\n" +
                "    \"waybillStatusValue\": \"\",\n" +
                "    \"feeStatus\": \"\"\n" +
                "  },\n" +
                "  \"pageSize\": 100,\n" +
                "  \"params\": {\n" +
                "    \"menuId\": 89\n" +
                "  },\n" +
                "  \"pageNum\": 1,\n" +
                "  \"userid\": 453358,\n" +
                "  \"superAdmin\": false\n" +
                "}";
        return JSON.parseObject(paramStr);
    }

}
