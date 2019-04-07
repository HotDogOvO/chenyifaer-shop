package com.chenyifaer.back.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

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
public class LogDTO extends PageDTO {

    /** 操作人 */
    @Length(max = 30 , message = "操作人不能超过30个字符")
    private String adminUserName;

    /** 动作 */
    @Length(max = 20 , message = "动作不能超过20个字符")
    private String action;

    /** 操作起始时间 */
    private String startTime;

    /** 操作结束时间 */
    private String endTime;

}
