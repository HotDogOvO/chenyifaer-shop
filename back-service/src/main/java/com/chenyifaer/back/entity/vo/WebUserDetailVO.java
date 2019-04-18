package com.chenyifaer.back.entity.vo;
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

import java.util.Date;

/**
 * 查詢用戶詳情 - VO
 * @Author:wudh
 * @Date: 2019/4/18 15:42
 */
@Data
@Accessors(chain = true)
public class WebUserDetailVO {

    /** 賬號 */
    private String userAccount;

    /** 昵称 */
    private String userNickName;

    /** 用戶渠道 */
    private Integer userType;

    /** 創建時間 */
    private Date createTime;

    /** 用戶性別 */
    private Integer userSex;

    /** 用戶真實姓名 */
    private String userName;

    /** 手機號 */
    private String userPhone;

    /** 郵箱 */
    private String userEmail;

    /** 用戶生日 */
    private String userBirthday;

    /** 收貨人姓名 */
    private String consigneeName;

    /** 收貨人手機號 */
    private String consigneePhone;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;

    /** 區域 */
    private String area;

    /** 詳細地址 */
    private String address;

    /** 經度 */
    private String longitude;

    /** 緯度 */
    private String latitude;

    /** 郵編 */
    private String zip;
}
