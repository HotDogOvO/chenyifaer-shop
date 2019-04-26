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
import java.util.List;

/**
 * 退单详情 - VO
 * @Author:wudh
 * @Date: 2019/4/26 12:35
 */
@Data
@Accessors(chain = true)
public class ReturnOrdersDetailVO {

    /** 退单流水号 */
    private String returnFlowNumber;

    /** 快递公司 */
    private String expressName;

    /** 快递单号 */
    private String expressNumber;

    /** 快递金额 */
    private BigDecimal expressPrice;

    /** 退货商品金额 */
    private BigDecimal returnGoodsPrice;

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

    /** 审核人姓名 */
    private String checkUserName;

    /** 審核失敗時間 */
    private Date checkFailTime;

    /** 退货成功时间 */
    private Date returnSuccessTime;

    /** 商品名稱 */
    private String goodsName;

    /** 購買用戶名 */
    private String userNickName;

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

    /** 訂單流水號 */
    private String flowNumber;

    /** 退单凭证集合 */
    private List<String> returnOrdersUrlList;

}
