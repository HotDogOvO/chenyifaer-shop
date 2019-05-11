package com.chenyifaer.web.redis;

import com.chenyifaer.web.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Redis操作方法 - 公用文件
 * @Author:wudh
 * @Date: 2019/5/11 12:12
 */
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisUtil redisUtil;

//    /**
//     * 用户新增购物车
//     * @Author:wudh
//     * @Date: 2019/5/11 12:12
//     */
//    public JsonResult addShopCart(Integer userId,Integer shopCartId){
//        //redis KEY为(CYFE:67373:R01:USERID:GOODSID)
//        StringBuffer sbfRedisKey = new StringBuffer(RedisConstant.REDIS_SHOPCART_R01);
//        sbfRedisKey.append(userId);
//
//        JSONArray jsonArray ;
//        //获取用户购物车信息
//        jsonArray = (JSONArray) redisUtil.get(sbfRedisKey.toString());
//        log.debug("【RUN】 - function end RedisService - addShopCart，查询出的用户购物车信息为：" + jsonArray);
//
//        //如果用户没有购物车信息，则进行初始化
//        if(jsonArray == null){
//            jsonArray = new JSONArray();
//        }else{
//            //判断当前商品是否已经存在
//            for (int i=0;i<jsonArray.size();i++){
//                ShopCartDTO dto = (ShopCartDTO) jsonArray.get(i);
//                //如果商品ID和SKUID均相等，则返回失败
//                if(dto.getGoodsId().equals(shopCartDTO.getGoodsId()) && dto.getShopSkuId().equals(shopCartDTO.getShopSkuId())){
//
//                }
//            }
//        }
//
//        jsonArray.add(shopCartId);
//        redisUtil.set(sbfRedisKey.toString(),jsonArray,RedisConstant.REDIS_SHOPCART_TIME);
//        //查询Redis是否插入成功
//        boolean flag = redisUtil.exists(sbfRedisKey.toString());
//        if(flag){
//            log.debug("【RUN】 - function end RedisService - addShopCart，新增购物车成功");
//            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
//        }
//        log.error("【RUN】 - function end RedisService - addShopCart，新增购物车失败，原因是：插入Redis失败");
//        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
//    }
//
//    /**
//     * 查询用户购物车
//     * @Author:wudh
//     * @Date: 2019/5/11 12:12
//     */
//    public List<Integer> getUserShopCart(Integer userId){
//        //redis KEY为(CYFE:67373:R01:USERID:GOODSID)
//        StringBuffer sbfRedisKey = new StringBuffer(RedisConstant.REDIS_SHOPCART_R01);
//        sbfRedisKey.append(userId);
//        //获取用户购物车信息
//        JSONArray jsonArray = (JSONArray) redisUtil.get(sbfRedisKey.toString());
//        List<Integer> list = jsonArray.toJavaList(Integer.class);
//
//        return list;
//    }

}
