package com.chenyifaer.app.constant;
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
 * 微信小程序调用接口地址
 * @Author:wudh
 * @Date: 2019/7/6 17:56
 */
public class WechatConstant {

    /** 登录地址 */
    public static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=${appId}&secret=${appSecret}&js_code=${jsCode}&grant_type=${grantType}";

    /** 获取AccessToken */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appId}&secret=${appSecret}";

    /** 云开发 - 获取文件上传链接 */
    public static final String UPLOAD_FILE_URL = "https://api.weixin.qq.com/tcb/uploadfile?access_token=${accessToken}";

}
