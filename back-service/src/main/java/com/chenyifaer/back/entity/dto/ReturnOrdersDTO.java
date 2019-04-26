package com.chenyifaer.back.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

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
 * 退單列表 - DTO
 * @Author:wudh
 * @Date: 2019/4/26 12:04
 */

@Data
@Accessors(chain = true)
public class ReturnOrdersDTO extends PageDTO {

    public interface getDetail{};

    public interface check{};

    /** 主键 */
    @NotNull(groups = {ReturnOrdersDTO.getDetail.class , ReturnOrdersDTO.check.class} , message = "returnOrdersId不能为空")
    private Integer returnOrdersId;

    /** 退單流水號 */
    @Length(max = 32 , message = "退单流水号不能超过32个字符")
    private String returnFlowNumber;

    /** 退單類型（1：退貨退款 2：僅退款） */
    private String returnType;

    /** 商品狀態（1：已收到貨 2：未收到貨） */
    private String goodsType;

    /** 状态（0：待审核 1：审核通过 2：退货中 3：退货成功 4：退货失败） */
    @NotNull(groups = {ReturnOrdersDTO.check.class} , message = "状态不能为空")
    private String status;

    /** 商品名稱 */
    @Length(max = 30 , message = "商品名称不能超过30个字符")
    private String goodsName;

    /** 購買用戶名 */
    @Length(max = 30 , message = "用户名不能超过30个字符")
    private String userNickName;

    /** 訂單流水號 */
    @Length(max = 32 , message = "订单流水号不能超过32个字符")
    private String flowNumber;

    /** 审核失败原因 */
    @Length(max = 200 , message = "审核失败原因不能超过200个字符")
    private String checkFailRemark;

    /** 起始時間 */
    private String startTime;

    /** 結束時間 */
    private String endTime;

}
