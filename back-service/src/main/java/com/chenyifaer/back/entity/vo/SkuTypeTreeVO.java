package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

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
 * SKU属性树状图 - VO
 * @Author:wudh
 * @Date: 2019/4/30 18:10
 */
@Data
@Accessors(chain = true)
public class SkuTypeTreeVO {

    /** 分类名 */
    private String typeName;

    /** 属性集合 */
    List<SkuValueTreeVO> valueTreeList;

}
