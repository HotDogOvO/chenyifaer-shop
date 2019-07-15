package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
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
 * 小程序端 - 最新动态 - VO
 * @Author:wudh
 * @Date: 2019/7/12 17:04
 */
@Data
@Accessors(chain = true)
public class AppNewsVO {

    /** 主键 */
    private Integer newsId;

    /** 标题 */
    private String newsName;

    /** 详情 */
    private String newsContent;

    /** 状态 */
    private Integer status;

    /** 创建时间 */
    private Date createTime;

    /** 图片集合 */
    private List<String> urlList;

}
