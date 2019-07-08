package com.chenyifaer.app.entity.vo;

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

@Data
@Accessors(chain = true)
public class NewsVO {

    /** 主键 */
    private Integer newsId;

    /** 标题 */
    private String newsName;

    /** 创建时间 */
    private Date createTime;

    /** 封面图 */
    private String url;

}
