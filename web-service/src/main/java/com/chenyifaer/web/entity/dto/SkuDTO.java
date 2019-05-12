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
 * SKU模块 - DTO
 * @Author:wudh
 * @Date: 2019/5/12 21:46
 */

@Data
@Accessors(chain = true)
public class SkuDTO {

    public interface getSkuValue{};

    /** 主键 */
    @NotNull(groups = {SkuDTO.getSkuValue.class} , message = "参数不能为空")
    private Integer skuKeyId;

    /** 获取商品起始位置 */
    private Integer startSize;

    /** 获取商品结束位置 */
    private Integer endSize;

}
