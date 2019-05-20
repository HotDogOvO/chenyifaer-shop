package com.chenyifaer.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.web.dao.ShopOrdersDao;
import com.chenyifaer.web.dao.ShopPayDao;
import com.chenyifaer.web.entity.po.ShopOrdersPO;
import com.chenyifaer.web.entity.po.ShopPayPO;
import com.chenyifaer.web.enums.PayStatusEnum;
import com.chenyifaer.web.service.ShopPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 支付管理 - 支付表 服务实现类
 * @author wudh
 * @since 2019-05-20
 */

@Slf4j
@Service
public class ShopPayServiceImpl extends ServiceImpl<ShopPayDao, ShopPayPO> implements ShopPayService {

    @Autowired
    private ShopPayDao shopPayDao;

    @Autowired
    private ShopOrdersDao shopOrdersDao;

    @Override
    public int addPay(String flowNumber, String payFlowNumber, Integer status) {
        log.debug("【START】 - function ShopPayServiceImpl - addPay");
        List<ShopOrdersPO> list = this.shopOrdersDao.selectList(new QueryWrapper<>(
                new ShopOrdersPO().setFlowNumber(flowNumber)));

        Integer ordersId = list.get(0).getOrdersId();
        log.debug("【RUN】 - function ShopPayServiceImpl - addPay，查询出的订单ID为：" + ordersId);

        ShopPayPO shopPayPO = new ShopPayPO().setPayFlowNumber(payFlowNumber);
        //判断支付状态
        if(status.equals(PayStatusEnum.PAY_STATUS_001.getCode())){
            shopPayPO.setPaySuccessTime(DateUtil.getTime());
        }
        if (status.equals(PayStatusEnum.PAY_STATUS_009.getCode())) {
            shopPayPO.setPayFailTime(DateUtil.getTime());
        }
        //根据订单ID更新
        int num = this.shopPayDao.update(shopPayPO,new QueryWrapper<>(new ShopPayPO().setOrdersId(ordersId)));
        log.debug("【RUN】 - function ShopPayServiceImpl - addPay，更新的订单信息为：" + shopPayPO);
        log.debug("【END】 - function ShopPayServiceImpl - addPay");
        return num;
    }
}
