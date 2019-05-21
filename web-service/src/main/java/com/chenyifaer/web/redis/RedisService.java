package com.chenyifaer.web.redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenyifaer.basic.common.constant.RedisConstant;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.web.entity.vo.GoodsVO;
import com.chenyifaer.web.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis操作方法 - 公用文件
 *
 * @Author:wudh
 * @Date: 2019/5/11 12:12
 */
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 新增商品
     * @Author:wudh
     * @Date: 2019/5/12 10:24
     */
    public void addGoods(List<GoodsVO> list) {
        log.debug("【START】 - function start RedisService - addGoods，开始新增商品");
        JSONArray goodsIdList = new JSONArray();
        //设定J为当前页数，K为插入Redis的时间
        int k = 0;
        int j = SystemConstant.GOODS_SEARCH_PAGE_INDED;
        for(int i = 0;i < list.size();i++){
            StringBuffer sbfRedisKey = new StringBuffer(RedisConstant.REDIS_GOODS_R01);
            sbfRedisKey.append(list.get(i).getGoodsId());
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(list.get(i));
            //插入商品信息
            redisUtil.set(sbfRedisKey.toString(), jsonObject);
            //当循环次数为最后一次时，将本次的ID插入集合中
            if(i == list.size()-1){
                goodsIdList.add(list.get(i).getGoodsId());
            }
            //如果k等于当前设置的页数，则将已有的ID集合插入Redis
            if((k == SystemConstant.GOODS_SEARCH_PAGE_SIZE || (i == list.size()-1))){
                //插入商品ID集合
                sbfRedisKey = new StringBuffer(RedisConstant.REDIS_GOODS_S01);
                sbfRedisKey.append(j);
                redisUtil.set(sbfRedisKey.toString(), goodsIdList);
                //重新赋值
                j++;
                k = 1;
                //重新设置idList，并将本次的ID插入
                goodsIdList = new JSONArray();
                goodsIdList.add(list.get(i).getGoodsId());
                continue;
            }
            goodsIdList.add(list.get(i).getGoodsId());
            k++;
        }
        log.debug("【END】 - function end RedisService - addGoods，新增商品结束");
    }

    /**
     * 查询商品信息
     * @Author:wudh
     * @Date: 2019/5/12 10:54
     */
    public List<GoodsVO> getGoodsList(int pageIndex){
        log.debug("【START】 - function start RedisService - getGoodsList，开始获取商品");
        //获取所有商品ID
        StringBuffer sbfRedisKey = new StringBuffer(RedisConstant.REDIS_GOODS_S01);
        sbfRedisKey.append(pageIndex);
        JSONArray jsonArray = (JSONArray) redisUtil.get(sbfRedisKey.toString());
        List<GoodsVO> list = new ArrayList<>();
        jsonArray.forEach(x -> {
            StringBuffer sb = new StringBuffer(RedisConstant.REDIS_GOODS_R01);
            sb.append(x);
            //获取商品信息
            JSONObject jsonObject = (JSONObject) redisUtil.get(sb.toString());
            list.add(jsonObject.toJavaObject(GoodsVO.class));
        });
        log.debug("【END】 - function end RedisService - getGoodsList，获取商品结束");
        return list;
    }

    /**
     * 邮箱验证码存入Redis
     * @Author:wudh
     * @Date: 2019/5/21 16:36
     */
    public void addEmailCode(int code,String email){
        log.debug("【START】 - function start RedisService - addEmailCode，新增邮箱验证码");
        //获取所有商品ID
        StringBuffer sbfRedisKey = new StringBuffer(RedisConstant.REDIS_EMAIL_CODE_R02);
        sbfRedisKey.append(email);

        //存入Redis，Key格式为：CYFE:67373:R02:Email地址，存储时间5分钟
        redisUtil.set(sbfRedisKey.toString(), code , RedisConstant.EMAIL_CODE_TIME);

        log.debug("【END】 - function end RedisService - addEmailCode");
    }

    /**
     * 获取邮箱验证码
     * @Author:wudh
     * @Date: 2019/5/21 19:35
     */
    public Integer getEmailCode(String email){
        log.debug("【START】 - function start RedisService - getEmailCode，获取邮箱验证码");
        //获取所有商品ID
        StringBuffer sbfRedisKey = new StringBuffer(RedisConstant.REDIS_EMAIL_CODE_R02);
        sbfRedisKey.append(email);

        //存入Redis，Key格式为：CYFE:67373:R02:Email地址，存储时间5分钟
        Integer code = 0;
        //验证码过期，返回0
        if(redisUtil.get(sbfRedisKey.toString()) != null){
            code = (int) redisUtil.get(sbfRedisKey.toString());
        }
        log.debug("【END】 - function end RedisService - getEmailCode");
        return code;
    }

}
