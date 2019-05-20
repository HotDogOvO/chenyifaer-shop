package com.chenyifaer.basic.common.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * OKHttp请求工具类
 * @Author:wudh
 * @Date: 2019/5/13 23:26
 */
public class OkHttpUtil {

    /** 请求数据格式 */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Get请求方式
     * @Author:wudh
     * @Date: 2019/5/13 23:26
     */
    public static JSONObject doGetJson(String url) {
        //新建OKHttpClient客户端
        OkHttpClient client = new OkHttpClient();
        //新建一个Request对象
        Request request = new Request.Builder().url(url).build();
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

    /**
     * Post请求方式
     * @Author:wudh
     * @Date: 2019/5/13 23:26
     */
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
