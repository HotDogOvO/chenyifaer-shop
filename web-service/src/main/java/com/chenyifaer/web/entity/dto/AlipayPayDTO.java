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

/**
 * 支付宝回调 - 系统内部处理参数 - 发送MQ
 * @Author:wudh
 * @Date: 2019/5/20 23:58
 */

@Data
@Accessors(chain = true)
public class AlipayPayDTO {

    /** 系统流水号 */
    private String flowNumber;

    /** 支付宝流水号 */
    private String payFlowNumber;

    /** 支付状态 */
    private Integer status;


}
