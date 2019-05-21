package com.chenyifaer.web.hanlder;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.chenyifaer.web.config.kdBirdConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 快递鸟对接 - 工具类
 * @Author:wudh
 * @Date: 2019/5/21 0:48
 */
@Slf4j
@Service
public class KDBirdHanlder {

    /** 商户ID */
    public final String eBusinessID = kdBirdConfig.eBusinessID;
    /** App密匙 */
    public final String appKey = kdBirdConfig.appKey;

}
