package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.dto.OrdersDTO;
import com.chenyifaer.web.entity.po.ShopOrdersPO;
import com.chenyifaer.web.entity.vo.UserOrdersVO;

import java.util.List;

/**
 * 订单管理 - 订单表 服务类
 * @author wudh
 * @since 2019-05-13
 */
public interface ShopOrdersService extends IService<ShopOrdersPO> {

    /**
     * 查询用户订单
     * @Author:wudh
     * @Date: 2019/5/21 12:11
     */
    List<UserOrdersVO> getList(OrdersDTO ordersDTO);

}
