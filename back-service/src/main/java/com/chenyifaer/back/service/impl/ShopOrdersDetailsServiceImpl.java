package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.OrdersDetailDTO;
import com.chenyifaer.back.entity.po.ShopOrdersDetailsPO;
import com.chenyifaer.back.dao.ShopOrdersDetailsDao;
import com.chenyifaer.back.entity.vo.OrdersDetailVO;
import com.chenyifaer.back.service.ShopOrdersDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理 - 订单详情表 服务实现类
 * @author wudh
 * @since 2019-04-25
 */
@Service
public class ShopOrdersDetailsServiceImpl extends ServiceImpl<ShopOrdersDetailsDao, ShopOrdersDetailsPO> implements ShopOrdersDetailsService {

    @Autowired
    private ShopOrdersDetailsDao shopOrdersDetailsDao;

    @Override
    public List<OrdersDetailVO> getOrdersDetail(OrdersDetailDTO ordersDetailDTO) {
        return this.shopOrdersDetailsDao.getOrdersDetail(ordersDetailDTO);
    }
}
