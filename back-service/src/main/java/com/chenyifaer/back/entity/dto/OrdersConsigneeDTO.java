package com.chenyifaer.back.entity.dto;
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
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 修改订单 - DTO
 * @Author:wudh
 * @Date: 2019/4/25 23:23
 */

@Data
@Accessors(chain = true)
public class OrdersConsigneeDTO {

    /** 主键 */
    @NotNull(message = "ordersId不能为空")
    private Integer ordersId;

    /** 收货人姓名 */
    @Length(max = 30 , message = "收货人姓名不能超过30个字符")
    private String consigneeName;

    /** 收货人手机号 */
    @Length(max = 11 , message = "手机号不能超过11个字符")
    private String consigneePhone;

    /** 省 */
    @Length(max = 30 , message = "省名称不能超过30个字符")
    private String province;

    /** 市 */
    @Length(max = 30 , message = "市名称不能超过30个字符")
    private String city;

    /** 区 */
    @Length(max = 30 , message = "区名称不能超过30个字符")
    private String area;

    /** 详细地址 */
    @Length(max = 100 , message = "详细地址名称不能超过100个字符")
    private String address;

}
