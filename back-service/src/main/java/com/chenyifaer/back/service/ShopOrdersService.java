package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.OrdersDTO;
import com.chenyifaer.back.entity.po.ShopOrdersPO;
import com.chenyifaer.back.entity.vo.OrdersVO;

import java.util.List;

/**
 * 订单管理 - 订单表 服务类
 * @author wudh
 * @since 2019-04-25
 */
public interface ShopOrdersService extends IService<ShopOrdersPO> {

    /**
     * 查询订单列表
     * @Author:wudh
     * @Date: 2019/4/25 20:16
     */
    List<OrdersVO> getList(OrdersDTO ordersDTO);
}
