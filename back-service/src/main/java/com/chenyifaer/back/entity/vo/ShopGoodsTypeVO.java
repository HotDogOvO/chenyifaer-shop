package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
 * 商品分类 - VO
 * @Author:wudh
 * @Date: 2019/4/25 11:25
 */
@Data
@Accessors(chain = true)
public class ShopGoodsTypeVO {

    /** 主键 */
    private Integer shopGoodsTypeId;

    /** 子分类姓名 */
    private String typeName;

    /** 父级分类ID */
    private Integer parentTypeId;

    /** 分类等级 */
    private Integer rank;

    /** 分类状态（0：禁用 1：启用） */
    private Integer status;

    /** 创建时间 */
    private Date createTime;

    /** 父级分类名称 */
    private String parentTypeName;

}
