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
 * 商品分类 - DTO
 * @Author:wudh
 * @Date: 2019/4/25 11:29
 */
@Data
@Accessors(chain = true)
public class ShopGoodsTypeDTO extends PageDTO {

    public interface Add{};

    public interface Update{};

    public interface Disable{};

    public interface GetTypeName{};

    /** 主键 */
    @NotNull(groups = {ShopGoodsTypeDTO.Update.class , ShopGoodsTypeDTO.Disable.class} , message = "shopGoodsTypeId不能为空")
    private Integer shopGoodsTypeId;

    /** 分类名称 */
    @NotNull(groups = {ShopGoodsTypeDTO.Add.class} , message = "分类名不能为空")
    @Length(max = 30 , message = "分类名称不能超过30个字符")
    private String typeName;

    /** 分类等级 */
    @NotNull(groups = {ShopGoodsTypeDTO.Add.class , ShopGoodsTypeDTO.GetTypeName.class} , message = "分类名不能为空")
    private Integer rank;

    /** 父级分类ID */
    @NotNull(groups = {ShopGoodsTypeDTO.Add.class} , message = "parentTypeId不能为空")
    private Integer parentTypeId;

    /** 分类状态 */
    @NotNull(groups = {ShopGoodsTypeDTO.Disable.class} , message = "状态不能为空")
    private Integer status;

    /** 父级分类名称 */
    @Length(max = 30 , message = "父分类名称不能超过30个字符")
    private String parentTypeName;

}
