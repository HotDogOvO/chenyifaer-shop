package com.chenyifaer.web.entity.vo;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户订单 - VO
 * @Author:wudh
 * @Date: 2019/5/21 12:08
 */

@Data
@Accessors(chain = true)
public class UserOrdersVO {

    /** 主键 */
    private Integer ordersId;

    /** 流水号 */
    private String flowNumber;

    /** 快递价格 */
    private BigDecimal expressPrice;

    /** 订单价格 */
    private BigDecimal ordersPrice;

    /** 狀態 */
    private Integer status;

    /** 下单时间 */
    private Date createTime;

    private List<UserOrdersDetailVO> ordersDetailList;

}
