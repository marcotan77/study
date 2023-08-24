package com.tan;

import com.sun.org.slf4j.internal.LoggerFactory;
import okhttp3.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author LvShuiQing.Nie
 * @version 1.0
 * @description:
 * @Date: 2022/2/12 10:22
 */
public class OkHttpUtils<T> {
//    private static Logger log = LoggerFactory.getLogger(OkHttpUtils.class);
    private static final String POST = "post";
    private static final String GET = "get";

    /**
     * post json 请求
     *
     * @param url
     * @param json
     * @param heads
     * @return
     */
    public static String postJson(String url, String json, Map<String, String> heads) {
        return okHttp(POST, url, json, heads);
    }

    /**
     * post json 请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String postJson(String url, String json) {
        return okHttp(POST, url, json, null);
    }

    /**
     * get 请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String getParams(String url, Map<String, String> params) {
        return okHttp(GET, url, params, null);
    }

    /**
     * post 表单请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String postParams(String url, Map<String, String> params) {
        return okHttp(POST, url, params, null);
    }

    private static <T> String okHttp(String method, String url, T params, Map<String, String> heads) {
        OkHttpClient client = getClient();
        //此处可设置请求头 .addHeader()
        Request.Builder builder = new Request.Builder();
        RequestFactory<T> requestFactory = initRequest(method);
        if (heads != null) {
            heads.keySet().forEach(s -> builder.addHeader(s, heads.get(s)));
        }
        Request request = requestFactory.initRequest(builder, url, params);
        try {
//            log.info("{}请求接口：{}，入参：{}", method, url, params);
            Response execute = client.newCall(request).execute();
            String result = Objects.requireNonNull(execute.body()).string();
//            log.info("{}请求接口：{}，出参：{}", method, url, result);
            return result;
        } catch (Exception e) {
//            log.error("{}请求失败，请求接口：{}。请求参数：{} [error] : ", method, url, params, e);
            return null;
        }
    }

    private static RequestFactory initRequest(String method) {
        if (GET.equals(method)) {
            return new GetRequest();
        } else if (POST.equals(method)) {
            return new PostRequest();
        }
        throw new RuntimeException("请求方式【method】应该是 post or get");
    }

    /**
     * 创建客户端
     *
     * @return
     */
    private static OkHttpClient getClient() {
        return new OkHttpClient().newBuilder()
                // 设置读取超时时间
                .readTimeout(60, TimeUnit.SECONDS)
                // 设置写的超时时间
                .writeTimeout(60, TimeUnit.SECONDS)
                // 设置连接超时时间
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 获取Request
     *
     * @param <T>
     */
    private interface RequestFactory<T> {
        Request initRequest(Request.Builder builder, String url, T params);
    }

    private static class PostRequest<T> implements RequestFactory<T> {
        @Override
        public Request initRequest(Request.Builder builder, String url, T params) {
            //json请求
            RequestBody body;
            if (params instanceof String) {
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                body = RequestBody.create(mediaType, (String) params);
            } else if (params instanceof Map) {
                //表单请求
                Map<String, String> map = (Map<String, String>) params;
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    formBodyBuilder.add(entry.getKey(), entry.getValue());
                }
                body = formBodyBuilder.build();
            } else {
//                log.error("post请求参数类型错误");
                throw new RuntimeException("post请求参数类型只能为 json 或者 表单");
            }
            return builder.url(url).post(body).build();
        }
    }

    private static class GetRequest implements RequestFactory<Map<String, String>> {

        @Override
        public Request initRequest(Request.Builder builder, String url, Map<String, String> params) {
            HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
            return new Request.Builder().url(httpBuilder.build()).get().build();
        }
    }
}