package com.chenyifaer.web.entity.dto;
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

import javax.validation.constraints.NotNull;

/**
 * 购物车 - DTO
 * @Author:wudh
 * @Date: 2019/5/11 12:23
 */

@Data
@Accessors(chain = true)
public class ShopCartDTO {

    public interface Select{};

    public interface Add{};

    public interface Update{};

    public interface Delete{};

    /** 主键 */
    @NotNull(groups = {ShopCartDTO.Delete.class,ShopCartDTO.Update.class} , message = "参数不能为空")
    private Integer shopCartId;

    /** 用户ID */
    @NotNull(groups = {ShopCartDTO.Select.class,ShopCartDTO.Add.class},message = "参数不能为空")
    private Integer userId;

    /** 商品ID */
    @NotNull(groups = {ShopCartDTO.Add.class},message = "参数不能为空")
    private Integer goodsId;

    /** 商品SKU - ID */
    @NotNull(groups = {ShopCartDTO.Add.class},message = "参数不能为空")
    private Integer shopSkuId;

    /** 商品数量 */
    @NotNull(groups = {ShopCartDTO.Add.class},message = "参数不能为空")
    private Integer goodsCount;

    /** 商品图片类型 */
    private Integer type;

}
