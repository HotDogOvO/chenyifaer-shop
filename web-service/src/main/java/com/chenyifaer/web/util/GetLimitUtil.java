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
        //取得随机数 num-2为了保证最少获取3条
        int startSize = random.nextInt(num-2);
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

    /**
     * 计算根据销量查询商品的起始数值
     * @Author:wudh
     * @Date: 2019/5/10 17:11
     */
    public static int getSalesGoodsStartSize(int num){
        Random random = new Random();
        //取得随机数 num-2为了保证最少获取3条
        int startSize = random.nextInt(num-2);
        //如果计算结果小于3，则让结果等于0
        if(startSize < SystemConstant.GOODS_SALES_SIZE){
            startSize = SystemConstant.LIMIT_START_SIZE;
        }
        return startSize;
    }

    /**
     * 计算查询二级分类的起始数值
     * @Author:wudh
     * @Date: 2019/5/12 21:23
     */
    public static int getTwoRankTypeStartSize(int num){
        Random random = new Random();
        //如果传递的数值减10小于或等于0，则在本身的范围内进行随机
        int startSize = random.nextInt((num-10 <= 0 ? num : num-10));
        //如果计算结果小于10，则让结果等于0
        if(startSize <= SystemConstant.GOODS_TWO_TYPE_SIZE){
            startSize = SystemConstant.LIMIT_START_SIZE;
        }
        return startSize;
    }

    /**
     * 计算查询SKU-KEY名称的起始数值
     * @Author:wudh
     * @Date: 2019/5/12 21:48
     */
    public static int getSkuKeyStartSize(int num){
        Random random = new Random();
        //如果传递的数值减10小于或等于0，则在本身的范围内进行随机
        int startSize = random.nextInt((num-10 <= 0 ? num : num-10));
        //如果计算结果小于3，则让结果等于0
        if(startSize <= SystemConstant.SKU_KEY_SIZE){
            startSize = SystemConstant.LIMIT_START_SIZE;
        }
        return startSize;
    }

}
