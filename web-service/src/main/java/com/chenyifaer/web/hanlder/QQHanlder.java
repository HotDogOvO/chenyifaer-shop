package com.chenyifaer.web.hanlder;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.alibaba.fastjson.JSONObject;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.basic.common.util.OkHttpUtil;
import com.chenyifaer.web.constant.QQConstant;
import com.chenyifaer.web.entity.dto.QQUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * QQ功能对接 - 工具类
 * @Author:wudh
 * @Date: 2019/7/29 11:38
 */
@Slf4j
@Service
public class QQHanlder {

//    /** appKey */
//    public final String appKey = QQConfig.appKey;
//    /** appSecret */
//    public final String appSecret = QQConfig.appSecret;
//    /** WeiboConfig */
//    public final String redirectUrl = QQConfig.redirectUrl;
//    /** grantType */
//    public final String grantType = QQConfig.grantType;
    /** appKey */
    public final String appKey = "101740252";
    /** appSecret */
    public final String appSecret = "4251d31abbb2413bb758ef90742049d9";
    /** WeiboConfig */
    public final String redirectUrl = "https://www.chenyifaer67373.com/loginRedirect";
    /** grantType */
    public final String grantType = "authorization_code";

    /**
     * 获取AccessToken
     * @Author:wudh
     * @Date: 2019/7/29 11:46
     */
    public String getAccessToken(String code){
        log.debug("【START】 - function QQHanlder - getAccessToken");
        log.debug("【RUN】 - function QQHanlder - getAccessToken - " +
                "appKey：【{}】，" +
                "appSecret：【{}】，" +
                "WeiboConfig：【{}】，" +
                "grantType：【{}】",appKey,appSecret,redirectUrl,grantType);

        String url = QQConstant.ACCESS_TOKEN_URL
                .replace("${appKey}",appKey)
                .replace("${appSecret}",appSecret)
                .replace("${redirectUrl}",redirectUrl)
                .replace("${grantType}",grantType)
                .replace("${code}",code);

        log.debug("【RUN】 - function QQHanlder - getAccessToken - 拼接后的url为：【{}】",url);
        //获取返回的字符串
        String strs = OkHttpUtil.doGetStr(url);
        log.debug("【RUN】 - function QQHanlder - getAccessToken - 返回的信息为：【{}】",strs);
        Map<String,Object> map = new HashMap<>(3);
        //根据- & -符号分割，循环取出AccessToken
        for(String str : strs.split(SystemConstant.SYSTEM_SYMBOLS_002)){
            //SystemConstant.SYSTEM_SYMBOLS_003为“ = ”
            map.put(str.substring(0,str.indexOf(SystemConstant.SYSTEM_SYMBOLS_003)),
                    str.substring(str.indexOf(SystemConstant.SYSTEM_SYMBOLS_003)+1));
        }
        String accessToken = map.get("access_token").toString();
        log.debug("【RUN】 - function QQHanlder - getAccessToken - 获取的accessToken为：【{}】",accessToken);
        return accessToken;
    }

    /**
     * 获取用户OpenId
     * @Author:wudh
     * @Date: 2019/7/29 13:57
     */
    public String getOpenId(String accessToken){
        log.debug("【START】 - function QQHanlder - getOpenId");
        String url = QQConstant.OPEN_ID_URL.replace("${accessToken}",accessToken);
        String strs = OkHttpUtil.doGetStr(url);
        log.debug("【RUN】 - function QQHanlder - getOpenId - 接收的返回信息为：【{}】",strs);
        String openId = null;
        //SystemConstant.SYSTEM_SYMBOLS_003为“ , ”
        for (String s : strs.split(SystemConstant.SYSTEM_SYMBOLS_004)) {
            //由于openId固定为32位，所以这里使用固定值截取
            openId = s.substring(10, s.length() - 6);
        }
        log.debug("【RUN】 - function QQHanlder - getOpenId - 取得的openId为：【{}】",openId);
        log.debug("【END】 - function QQHanlder - getOpenId");
        return openId;
    }

    /**
     * 获取用户详细信息
     * @Author:wudh
     * @Date: 2019/7/29 14:45
     */
    public QQUserDTO getUserDetail(String accessToken, String openId){
        log.debug("【START】 - function QQHanlder - getUserDetail");
        String url = QQConstant.USER_DETAIL_URL
                .replace("${accessToken}",accessToken)
                .replace("${openId}",openId)
                .replace("${appKey}",appKey);
        JSONObject jsonObject = OkHttpUtil.doGetJson(url);
        log.debug("【RUN】 - function QQHanlder - getUserDetail - 接收的返回值为：【{}】",jsonObject);
        QQUserDTO userQQDTO = jsonObject.toJavaObject(QQUserDTO.class);
        log.debug("【END】 - function QQHanlder - getUserDetail");
        return userQQDTO;
    }

}
