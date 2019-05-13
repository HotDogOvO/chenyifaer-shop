package com.chenyifaer.web.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

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
 * 用户地址 - VO
 * @Author:wudh
 * @Date: 2019/5/13 14:03
 */
@Data
@Accessors(chain = true)
public class UserAddressVO {

    /** 主键 */
    private Integer addressId;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人手机号 */
    private String consigneePhone;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 区 */
    private String area;

    /** 详细地址 */
    private String address;

    /** 是否默认地址（0：否 1：是） */
    private Integer defaultStatus;

}
