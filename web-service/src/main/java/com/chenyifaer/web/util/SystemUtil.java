package com.chenyifaer.web.util;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.basic.common.util.DateUtil;

import java.util.Date;
import java.util.Random;

/**
 * 系统工具类
 * @Author:wudh
 * @Date: 2019/5/13 12:38
 */
public class SystemUtil {

    /**
     * 生成订单流水号
     * @Author:wudh
     * @Date: 2019/5/13 12:38
     */
    public static String getFlowNumber(){
        Random r = new Random();
        int flag = r.nextInt(999999);
        //如果计算结果小于100000，则结果为随机数+100000
        if(flag < SystemConstant.ORDERS_FLOWNUMBER_SIZE){
            flag += SystemConstant.ORDERS_FLOWNUMBER_SIZE;
        }
        String flowNumber = DateUtil.getYYYYMMddHHmmssDate(new Date()) + flag;
        return flowNumber;
    }

}
