package com.chenyifaer.back.entity.dto;

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

@Data
@Accessors(chain = true)
public class GoodsSkuDTO {

    public interface Select{};

    public interface Add{};

    /** 主键 */
    @NotNull(groups = {GoodsSkuDTO.Select.class,GoodsSkuDTO.Add.class} , message = "goodsId不能为空")
    private Integer goodsId;

    /** SKUID */
    @NotNull(groups = {GoodsSkuDTO.Add.class} , message = "shopSkuId不能为空")
    private List<String> shopSkuIdList;



}
