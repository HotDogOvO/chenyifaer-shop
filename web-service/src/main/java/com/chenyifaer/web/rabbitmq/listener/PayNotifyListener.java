package com.chenyifaer.web.rabbitMq.listener;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.web.constant.RabbitMqConstant;
import com.chenyifaer.web.dao.ShopOrdersDao;
import com.chenyifaer.web.dao.ShopPayDao;
import com.chenyifaer.web.entity.dto.AlipayPayDTO;
import com.chenyifaer.web.entity.po.ShopOrdersPO;
import com.chenyifaer.web.entity.po.ShopPayPO;
import com.chenyifaer.web.enums.OrdersStatusEnum;
import com.chenyifaer.web.enums.PayStatusEnum;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 支付宝异步回调 - 系统内部处理
 * @Author:wudh
 * @Date: 2019/5/21 0:10
 */
@Slf4j
@Service
public class PayNotifyListener {

    @Autowired
    private ShopPayDao shopPayDao;

    @Autowired
    private ShopOrdersDao shopOrdersDao;

    /**
     * 支付宝异步回调 - 系统内部处理
     * @Author:wudh
     * @Date: 2019/5/21 0:10
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitMqConstant.PAY_NOTIFY_QUEUE),
            exchange = @Exchange(value = RabbitMqConstant.EXCHANGE,type = RabbitMqConstant.EXCHANGE_TYPE),
            key = RabbitMqConstant.PAY_NOTIFY_ROUTING_KEY
    ))
    public void payNotifyListener(String str, @Headers Map<String, Object> headers, Channel channel){
        log.debug("【START】 - function payNotifyListener 开始监听");
        try{
            //参数接收
            JSONObject jsonObject = JSONObject.parseObject(str);
            AlipayPayDTO alipayPayDTO = JSONObject.toJavaObject(jsonObject,AlipayPayDTO.class);

            List<ShopOrdersPO> list = this.shopOrdersDao.selectList(new QueryWrapper<>(
                    new ShopOrdersPO().setFlowNumber(alipayPayDTO.getFlowNumber())));

            Integer ordersId = list.get(0).getOrdersId();
            log.debug("【RUN】 - function payNotifyListener，查询出的订单ID为：" + ordersId);

            ShopPayPO shopPayPO = new ShopPayPO().setPayFlowNumber(alipayPayDTO.getPayFlowNumber());
            ShopOrdersPO shopOrdersPO = new ShopOrdersPO().setOrdersId(ordersId).setPayTime(DateUtil.getTime());
            //判断支付状态
            if(alipayPayDTO.getStatus().equals(PayStatusEnum.PAY_STATUS_001.getCode())){
                shopPayPO.setStatus(PayStatusEnum.PAY_STATUS_001.getCode());
                shopPayPO.setPaySuccessTime(DateUtil.getTime());
                //如果支付成功，修改订单状态为已支付
                shopOrdersPO.setStatus(OrdersStatusEnum.STATUS_001.getCode());
            }
            if (alipayPayDTO.getStatus().equals(PayStatusEnum.PAY_STATUS_009.getCode())) {
                shopPayPO.setStatus(PayStatusEnum.PAY_STATUS_009.getCode());
                shopPayPO.setPayFailTime(DateUtil.getTime());
                //如果支付失败，修改订单状态为已取消
                shopOrdersPO.setStatus(OrdersStatusEnum.STATUS_007.getCode());
            }
            //根据订单ID更新
            this.shopPayDao.update(shopPayPO,new QueryWrapper<>(new ShopPayPO().setOrdersId(ordersId)));
            log.debug("【RUN】 - function payNotifyListener，更新的支付信息为：" + shopPayPO);
            //更新订单信息
            this.shopOrdersDao.updateById(shopOrdersPO);

            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            //ACK - 确认签收
            try {
                channel.basicAck(deliveryTag, false);
                log.debug("【RUN】 - function payNotifyListener 确认签收");
            } catch (IOException e) {
                log.error("【ERROR】 - function payNotifyListener 签收失败，原因是：{}",e);
                e.printStackTrace();
            }
        } catch(Exception e){
            log.error("【ERROR】 - function payNotifyListener 监听错误，原因是：{}",e);
            e.printStackTrace();
        }
        log.debug("【END】 - function payNotifyListener 结束监听");
    }

}
