package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.dto.OrdersDTO;
import com.chenyifaer.web.entity.po.ShopOrdersPO;
import com.chenyifaer.web.dao.ShopOrdersDao;
import com.chenyifaer.web.entity.vo.UserOrdersVO;
import com.chenyifaer.web.enums.GoodsImgEnum;
import com.chenyifaer.web.enums.OrdersDeleteStatusEnum;
import com.chenyifaer.web.service.ShopOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理 - 订单表 服务实现类
 * @author wudh
 * @since 2019-05-13
 */
@Service
public class ShopOrdersServiceImpl extends ServiceImpl<ShopOrdersDao, ShopOrdersPO> implements ShopOrdersService {

    @Autowired
    private ShopOrdersDao shopOrdersDao;

    @Override
    public List<UserOrdersVO> getList(OrdersDTO ordersDTO) {
        ordersDTO.setType(GoodsImgEnum.IMG_TYPE_001.getCode())
                .setDeleteStatus(OrdersDeleteStatusEnum.STATUS_001.getCode());

        return this.shopOrdersDao.getList(ordersDTO);
    }
}
