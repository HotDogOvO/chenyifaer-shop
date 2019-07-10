package com.chenyifaer.app.task;

import com.chenyifaer.app.handler.WechatHanlder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
 * 定时任务
 * @Author:wudh
 * @Date: 2019/7/9 11:59
 */
@Slf4j
@Component
public class TaskService {

    @Autowired
    private WechatHanlder wechatHanlder;

    /**
     * 每90分钟 刷新一次accessToken
     * @Author:wudh
     * @Date: 2019/7/9 11:59
     */
    @Scheduled(fixedRate = 1000 * 60 * 90)
    public void flushAccessToken(){
        wechatHanlder.setAccessToken();
    }

}
