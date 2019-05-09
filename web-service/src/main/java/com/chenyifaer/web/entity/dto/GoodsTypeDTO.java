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
 * 商品分类 - DTO
 * @Author:wudh
 * @Date: 2019/5/8 17:38
 */
@Data
@Accessors(chain = true)
public class GoodsTypeDTO {

    public interface getTypeById{};

    /** 商品分类父ID */
    @NotNull(groups = {GoodsTypeDTO.getTypeById.class} , message = "分类ID不能为空")
    private Integer parentTypeId;

    /** 获取起始位置 */
    private Integer startSize;

    /** 获取结束位置 */
    private Integer endSize;

}
