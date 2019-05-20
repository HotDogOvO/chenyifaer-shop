package com.chenyifaer.basic.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
 * 日期处理工具类
 * @Author:wudh
 * @Date: 2019/4/7 18:37
 */
public class DateUtil {

    /**
     * 获取当前系统时间
     * @Author:wudh
     * @Date: 2019/4/7 18:44
     */
    public static LocalDateTime getTime(){
        return LocalDateTime.now();
    }

    /**
     * 获取yyyy/MM/dd格式
     * @Author:wudh
     * @Date: 2019/4/21 17:48
     */
    public static String getYYYYMMDDBySprit(Date date) {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String time = sdf.format(date);
        return time;
    }

    /**
     * 获取时间 HH:mm:ss格式
     * @Author:wudh
     * @Date: 2019/4/21 17:53
     */
    public static String getHHmmssDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String time = sdf.format(date);
        return time;
    }

    /**
     * 获取时间 yyyyMMddHHmmss格式
     * @Author:wudh
     * @Date: 2019/5/13 12:42
     */
    public static String getYYYYMMddHHmmssDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(date);
        return time;
    }

    /**
     * 获取时间 yyyy-MM-dd HH:mm:ss格式
     * @Author:wudh
     * @Date: 2019/5/14 0:02
     */
    public static String getDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }


}
