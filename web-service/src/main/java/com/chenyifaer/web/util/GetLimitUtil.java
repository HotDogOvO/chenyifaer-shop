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

import java.util.Random;

/**
 * 计算SQL LIMIT数值 - 工具类
 * @Author:wudh
 * @Date: 2019/5/7 22:17
 */
public class GetLimitUtil {

    /**
     * 计算推荐商品起始数值
     * @Author:wudh
     * @Date: 2019/5/7 22:29
     */
    public static int getRecommendStartSize(int num){
        Random random = new Random();
        //取得随机数 num+1为了可以获取num本身
        int startSize = random.nextInt(num-1);
        //如果计算结果小于3，则让结果等于0
        if(startSize < SystemConstant.GOODS_RECOMMENDED_SIZE){
            startSize = SystemConstant.LIMIT_START_SIZE;
        }
        return startSize;
    }

    /**
     * 计算支持优惠券商品起始数值
     * @Author:wudh
     * @Date: 2019/5/7 22:29
     */
    public static int getCouponsStartSize(int num){
        Random random = new Random();
        //取得随机数 num+1为了可以获取num本身
        int startSize = random.nextInt(num-1);
        //如果计算结果小于2，则让结果等于0
        if(startSize < SystemConstant.GOODS_COUPONS_SIZE){
            startSize = SystemConstant.LIMIT_START_SIZE;
        }
        return startSize;
    }

    /**
     * 计算支持积分商品起始数值
     * @Author:wudh
     * @Date: 2019/5/7 22:29
     */
    public static int getIntegralStartSize(int num){
        Random random = new Random();
        //取得随机数 num+1为了可以获取num本身
        int startSize = random.nextInt(num-1);
        //如果计算结果小于2，则让结果等于0
        if(startSize < SystemConstant.GOODS_INTEGRAL_SIZE){
            startSize = SystemConstant.LIMIT_START_SIZE;
        }
        return startSize;
    }

}
