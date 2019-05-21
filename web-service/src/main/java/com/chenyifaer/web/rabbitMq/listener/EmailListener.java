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
import com.chenyifaer.web.constant.RabbitMqConstant;
import com.chenyifaer.web.entity.dto.EmailDTO;
import com.chenyifaer.web.redis.RedisService;
import com.chenyifaer.web.util.EmailUtil;
import com.chenyifaer.web.util.SystemUtil;
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
import java.util.Map;

/**
 * 发送邮件 - MQ监听者
 * @Author:wudh
 * @Date: 2019/5/21 16:24
 */
@Slf4j
@Service
public class EmailListener {

    @Autowired
    private RedisService redisService;

    /**
     * 发送邮件
     * @Author:wudh
     * @Date: 2019/5/21 16:24
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitMqConstant.EMAIL_QUEUE),
            exchange = @Exchange(value = RabbitMqConstant.EXCHANGE,type = RabbitMqConstant.EXCHANGE_TYPE),
            key = RabbitMqConstant.EMAIL_ROUTING_KEY
    ))
    public void emailListener(String str, @Headers Map<String, Object> headers, Channel channel){
        log.debug("【START】 - function emailListener 开始监听");
        try{
            //参数接收
            JSONObject jsonObject = JSONObject.parseObject(str);
            EmailDTO emailDTO = JSONObject.toJavaObject(jsonObject,EmailDTO.class);
            //生成随机数
            int code = SystemUtil.getCode();
            //验证码存入Redis
            this.redisService.addEmailCode(code,emailDTO.getEmail());

            //发送邮件
            EmailUtil.sendEmail(
                    emailDTO.getEmail(),
                    emailDTO.getEmailTitle(),
                    //替换验证码
                    emailDTO.getEmailText().replace("${code}",String.valueOf(code)));

            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            //ACK - 确认签收
            try {
                channel.basicAck(deliveryTag, false);
                log.debug("【RUN】 - function emailListener 确认签收");
            } catch (IOException e) {
                log.error("【ERROR】 - function emailListener 签收失败，原因是：{}",e);
                e.printStackTrace();
            }
        } catch(Exception e){
            log.error("【ERROR】 - function emailListener 监听错误，原因是：{}",e);
            e.printStackTrace();
        }
        log.debug("【END】 - function emailListener 结束监听");
    }

}
