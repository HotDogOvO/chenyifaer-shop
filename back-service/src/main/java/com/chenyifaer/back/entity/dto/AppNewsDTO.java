package com.chenyifaer.back.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;

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
 * 小程序端 - 最新动态 - DTO
 * @Author:wudh
 * @Date: 2019/7/12 17:07
 */
@Data
@Accessors(chain = true)
public class AppNewsDTO extends PageDTO {

    /** 主键 */
    private Integer newsId;

    /** 标题 */
    private String newsName;

    /** 状态 */
    private Integer status;

    /** 起始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;
}
