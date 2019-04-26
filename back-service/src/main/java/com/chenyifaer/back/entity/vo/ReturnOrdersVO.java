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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 退单列表 - VO
 * @Author:wudh
 * @Date: 2019/4/26 11:57
 */

@Data
@Accessors(chain = true)
public class ReturnOrdersVO {

    /** 主键 */
    private Integer returnOrdersId;

    /** 退单流水号 */
    private String reutrnFlowNumber;

    /** 退單金額 */
    private BigDecimal returnOrdersPrice;

    /** 退單類型（1：退貨退款 2：僅退款） */
    private String returnType;

    /** 商品狀態（1：已收到貨 2：未收到貨） */
    private String goodsType;

    /** 状态（0：待审核 1：审核通过 2：退货中 3：退货成功 4：退货失败） */
    private String status;

    /** 退單備註 */
    private String retrunRemark;

    /** 審核失敗備註 */
    private String checkFailRemark;

    /** 創建時間 */
    private Date createTime;

    /** 審核時間 */
    private Date checkTime;

    /** 審核失敗時間 */
    private Date checkFailTime;

    /** 商品名稱 */
    private String goodsName;

    /** 購買用戶名 */
    private String userNickName;

    /** 訂單流水號 */
    private String flowNumber;

    /** 审核人姓名 */
    private String checkUserName;

}
