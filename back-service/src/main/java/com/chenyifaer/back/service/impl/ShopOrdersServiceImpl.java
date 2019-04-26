package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.OrdersDTO;
import com.chenyifaer.back.entity.po.ShopOrdersPO;
import com.chenyifaer.back.dao.ShopOrdersDao;
import com.chenyifaer.back.entity.vo.OrdersVO;
import com.chenyifaer.back.service.ShopOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理 - 订单表 服务实现类
 * @author wudh
 * @since 2019-04-25
 */
@Service
public class ShopOrdersServiceImpl extends ServiceImpl<ShopOrdersDao, ShopOrdersPO> implements ShopOrdersService {

    @Autowired
    private ShopOrdersDao shopOrdersDao;

    @Override
    public List<OrdersVO> getList(OrdersDTO ordersDTO) {
        return this.shopOrdersDao.getList(ordersDTO);
    }
}
