package com.chenyifaer.back.entity.vo;
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

import java.util.Date;
import java.util.List;

/**
 * SkuKey列表 - VO
 * @Author:wudh
 * @Date: 2019/4/30 13:32
 */

@Data
@Accessors(chain = true)
public class SkuKeyVO {

    /** 主键 */
    private Integer skuKeyId;

    /** key值 */
    private String keyName;

    /** 创建时间 */
    private Date createTime;

    /** value值集合 */
    private List<SkuKeyValueVO> valueList;

}
