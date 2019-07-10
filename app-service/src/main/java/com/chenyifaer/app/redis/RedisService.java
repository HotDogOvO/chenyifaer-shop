package com.chenyifaer.app.redis;

import com.chenyifaer.app.util.RedisUtil;
import com.chenyifaer.basic.common.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Redis操作方法 - 公用文件
 *
 * @Author:wudh
 * @Date: 2019/5/11 12:12
 */
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取微信AccessToken
     * @Author:wudh
     * @Date: 2019/7/9 11:28
     */
    public String getAccessToken(){
        log.debug("【START】 - function RedisService - getAccessToken");
        String accessToken = redisUtil.get(RedisConstant.REDIS_WECHAT_ACCESSTOKEN_W01).toString();
        log.debug("【END】 - function RedisService - getAccessToken");
        return accessToken;
    }

    /**
     * 微信AccessToken存入redis , 时间为5400秒
     * @Author:wudh
     * @Date: 2019/7/9 11:00
     */
    public boolean setAccessToken(String accessToken){
        log.debug("【START】 - function RedisService - setAccessToken");
        //插入Key值：CYFE:67373:W01:1  时间为5400秒
        boolean flag = redisUtil.set(
                RedisConstant.REDIS_WECHAT_ACCESSTOKEN_W01,
                accessToken,
                RedisConstant.ACCESS_TOKEN_TIME);
        log.debug("【END】 - function RedisService - setAccessToken - 插入的accessToken为【{}】", accessToken);
        return flag;
    }
}
