package com.chenyifaer.web.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

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
 * 订单模块 - DTO
 * @Author:wudh
 * @Date: 2019/5/13 12:30
 */
@Data
@Accessors(chain = true)
public class OrdersDTO {

    public interface AddOrders{};

    public interface GetOrdersDetail{};

    public interface ConfirmOrders{};

    /** 用户ID */
    @NotNull(groups = {OrdersDTO.AddOrders.class} , message = "参数不能为空")
    private Integer userId;

    /** 订单商品集合 */
    @NotNull(groups = {OrdersDTO.AddOrders.class,OrdersDTO.ConfirmOrders.class} , message = "参数不能为空")
    private List<OrdersGoodsDTO> goodsList;

    /** 订单流水号 */
    @NotNull(groups = {OrdersDTO.GetOrdersDetail.class,OrdersDTO.ConfirmOrders.class} , message = "参数不能为空")
    private String flowNumber;

    /** 收货地址ID */
    @NotNull(groups = {OrdersDTO.ConfirmOrders.class} , message = "参数不能为空")
    private Integer addressId;

    /** 备注 */
    private String remark;

    /** 是否支持积分 */
    @NotNull(groups = {OrdersDTO.ConfirmOrders.class} , message = "参数不能为空")
    private Integer integralStatus;

    /** 是否支持优惠券 */
    @NotNull(groups = {OrdersDTO.ConfirmOrders.class} , message = "参数不能为空")
    private Integer couponsStatus;

}
