package com.chenyifaer.web.rabbitMq.send;
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
import com.chenyifaer.web.entity.dto.AlipayPayDTO;
import com.chenyifaer.web.entity.dto.EmailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RabbitMq请求发送
 * @Author:wudh
 * @Date: 2019/5/20 23:53
 */

@Slf4j
@Service
public class SendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 支付异步回调 - 系统内部处理
     * @Author:wudh
     * @Date: 2019/5/20 23:53
     */
    public void payNotify(AlipayPayDTO alipayPayDTO) {
        log.debug("【START】 - function payNotify");
        //MQ请求唯一标识
        CorrelationData correlationData = new CorrelationData(alipayPayDTO.getFlowNumber());

        try {
            rabbitTemplate.convertAndSend(
                //交换机
                RabbitMqConstant.EXCHANGE,
                //交换口令
                RabbitMqConstant.PAY_NOTIFY_ROUTING_KEY,
                //传递JSON数据
                JSONObject.toJSONString(alipayPayDTO),
                correlationData);
        } catch (AmqpException e) {
            log.error("【ERROR】 - function payNotify:{}", e);
        }
        log.debug("【END】 - function payNotify");
    }

    /**
     * 邮件发送
     * @Author:wudh
     * @Date: 2019/5/21 16:19
     */
    public void email(EmailDTO emailDTO){
        log.debug("【START】 - function email");
        //MQ请求唯一标识
        CorrelationData correlationData = new CorrelationData(emailDTO.getEmail());

        try {
            rabbitTemplate.convertAndSend(
                    //交换机
                    RabbitMqConstant.EXCHANGE,
                    //交换口令
                    RabbitMqConstant.EMAIL_ROUTING_KEY,
                    //传递JSON数据
                    JSONObject.toJSONString(emailDTO),
                    correlationData);
        } catch (AmqpException e) {
            log.error("【ERROR】 - function email:{}", e);
        }
        log.debug("【END】 - function email");
    }
}
