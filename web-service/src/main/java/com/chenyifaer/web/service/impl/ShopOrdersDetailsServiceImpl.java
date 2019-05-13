package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.dto.OrdersDetailDTO;
import com.chenyifaer.web.entity.po.ShopOrdersDetailsPO;
import com.chenyifaer.web.dao.ShopOrdersDetailsDao;
import com.chenyifaer.web.entity.vo.OrdersDetailVO;
import com.chenyifaer.web.enums.GoodsImgEnum;
import com.chenyifaer.web.service.ShopOrdersDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理 - 订单详情表 服务实现类
 * @author wudh
 * @since 2019-05-13
 */
@Service
public class ShopOrdersDetailsServiceImpl extends ServiceImpl<ShopOrdersDetailsDao, ShopOrdersDetailsPO> implements ShopOrdersDetailsService {

    @Autowired
    private ShopOrdersDetailsDao shopOrdersDetailsDao;

    @Override
    public List<OrdersDetailVO> getOrdersDetail(OrdersDetailDTO ordersDetailDTO) {
        ordersDetailDTO.setType(GoodsImgEnum.IMG_TYPE_001.getCode());
        return this.shopOrdersDetailsDao.getOrdersDetail(ordersDetailDTO);
    }
}
