package com.chenyifaer.app.handler;
/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

import com.alibaba.fastjson.JSONObject;
import com.chenyifaer.app.config.WechatConfig;
import com.chenyifaer.app.constant.WechatConstant;
import com.chenyifaer.app.entity.dto.WechatTokenDTO;
import com.chenyifaer.app.entity.dto.WechatUploadDTO;
import com.chenyifaer.app.redis.RedisService;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.basic.common.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 微信小程序 - 第三方接口
 * @Author:wudh
 * @Date: 2019/7/9 10:47
 */
@Slf4j
@Component
public class WechatHanlder {

    @Autowired
    private RedisService redisService;

    /**
     * 获取微信AccessTokne
     * @Author:wudh
     * @Date: 2019/7/9 11:27
     */
    public String getAccessToken(){
        log.debug("【START】 - function WechatHanlder - getAccessToken");
        String accessToken = "";
        try{
            accessToken = redisService.getAccessToken();
            //判断当前token是否为空，如果为空，则重新获取
            if(accessToken.isEmpty()){
                log.debug("【RUN】 - function WechatHanlder - getAccessToken - token过期了，重新获取");
                this.setAccessToken();
            }
        } catch(Exception e){
            log.error("【ERROR】 - function WechatHanlder - getAccessToken - 获取token出现异常，原因是【{}】",e);
            e.printStackTrace();
        }
        log.debug("【END】 - function WechatHanlder - getAccessToken - 获取的token为【{}】");
        return accessToken;
    }

    /**
     * 插入Redis - 微信AccessToken
     * @Author:wudh
     * @Date: 2019/7/9 10:48
     */
    public void setAccessToken() {
        log.debug("【START】 - function WechatHanlder - setAccessToken");

        JSONObject jsonObject = OkHttpUtil.doGetJson(WechatConstant.ACCESS_TOKEN_URL
                .replace("${appId}", WechatConfig.appId)
                .replace("${appSecret}", WechatConfig.appSecret));
        WechatTokenDTO wechatTokenDTO = jsonObject.toJavaObject(WechatTokenDTO.class);
        log.debug("【RUN】 - function WechatHanlder - setAccessToken - 获取的数据为：【{}】",wechatTokenDTO);

        String accessToken = wechatTokenDTO.getAccess_token();
        //判断返回的值是否为空
        try{
            if(!accessToken.isEmpty()){
                //如果不为空，将accessToken插入redis，存储时间 6000秒
                boolean flag = redisService.setAccessToken(accessToken);
                if(flag){
                    log.debug("【END】 - function WechatHanlder - setAccessToken - 插入微信token成功");
                }else{
                    log.error("【END】 - function WechatHanlder - setAccessToken - 插入微信token失败");
                }
            }
        } catch(Exception e){
            log.error("【END】 - function WechatHanlder - setAccessToken - 获取微信token出现异常，原因是：【{}】", e);
            e.printStackTrace();
        }
    }

    /**
     * 微信小程序 - 云文件上传
     * @Author:wudh
     * @Date: 2019/7/9 13:18
     */
    public void cloudUpload(String mkdirPath, String fileName, MultipartFile file){
        //获取accessToken
        String accessToken = redisService.getAccessToken();
        //创建请求参数
        JSONObject json = new JSONObject();
        StringBuffer sb = new StringBuffer(WechatConfig.imgPath);
        sb.append(mkdirPath).append(SystemConstant.SYSTEM_SYMBOLS_001).append(fileName);
        json.put("env",WechatConfig.env);
        json.put("path",sb);
        //提交请求 （获取文件上传链接）
        JSONObject jsonObject = OkHttpUtil.doPostJson(
                WechatConstant.UPLOAD_FILE_URL.replace("${accessToken}",accessToken),
                json.toString());
        //接收返回数据
        WechatUploadDTO wechatUploadDTO = jsonObject.toJavaObject(WechatUploadDTO.class);

        //提交云文件上传请求


        System.out.println(jsonObject);

    }

}
