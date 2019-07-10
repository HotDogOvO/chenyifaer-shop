package com.chenyifaer.app.entity.dto;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取云开发 - 文件上传链接参数
 * @Author:wudh
 * @Date: 2019/7/9 13:33
 */
@Data
@Accessors(chain = true)
public class WechatUploadDTO {

    /** 返回值 */
    private String errcode;

    /** 返回信息 */
    private String errmsg;

    /** 文件上传请求路径 */
    private String url;

    /** token */
    private String token;

    /** authorization */
    private String authorization;

    /** 文件ID */
    private String fileId;

    /** cos文件ID */
    private String cosFileId;

}
