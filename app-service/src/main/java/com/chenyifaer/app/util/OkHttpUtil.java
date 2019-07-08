package com.chenyifaer.app.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class OkHttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static JSONObject doGetJson(String url) {
        //新建OKHttpClient客户端
        OkHttpClient client = new OkHttpClient();
        //新建一个Request对象
        Request request = new Request.Builder().url(url).get().build();
        JSONObject jsonObject = null;
        try {
            //Response为OKHttp中的响应
            Response response = client.newCall(request).execute();
            //用FastJson对数据进行转换
            jsonObject = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject doPostJson(String url,String json) {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2：传递的json串)
        RequestBody requestBody = RequestBody.create(JSON , json);
        //创建一个请求对象
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //用FastJson对数据进行转换
        JSONObject jsonObject = null;
        try {
            //发送请求获取响应
            Response response=okHttpClient.newCall(request).execute();
            jsonObject = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
