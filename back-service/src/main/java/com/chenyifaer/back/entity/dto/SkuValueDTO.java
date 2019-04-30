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

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Sku属性 - DTO
 * @Author:wudh
 * @Date: 2019/4/30 15:25
 */
@Data
@Accessors(chain = true)
public class SkuValueDTO extends PageDTO {

    public interface Add{};

    public interface Update{};

    public interface Delete{};

    /** 主键 */
    @NotNull(groups = {SkuValueDTO.Delete.class,SkuValueDTO.Update.class} , message = "skuValueId不能为空")
    private Integer skuValueId;

    /** 属性名 */
    @NotNull(groups = {SkuValueDTO.Add.class} , message = "属性名不能为空")
    private String valueName;

    /** 分类ID */
    @NotNull(groups = {SkuValueDTO.Add.class} , message = "分类不能为空")
    private Integer shopGoodsTypeId;

    /** 起始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

}
