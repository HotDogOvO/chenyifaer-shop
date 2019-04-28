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
 * 轮播图管理 - DTO
 * @Author:wudh
 * @Date: 2019/4/28 13:24
 */

@Data
@Accessors(chain = true)
public class BannerDTO extends PageDTO {

    public interface Add{};

    /** 轮播图名称 */
    @NotNull(groups = {BannerDTO.Add.class} , message = "轮播图名称不能为空")
    private String bannerName;

    /** 轮播图路径 */
    @NotNull(groups = {BannerDTO.Add.class} , message = "轮播图路径不能为空")
    private String bannerImageUrl;

    /** 权重 */
    @NotNull(groups = {BannerDTO.Add.class} , message = "权重不能为空")
    private Integer weight;

    /** 状态 */
    private Integer status;

}
