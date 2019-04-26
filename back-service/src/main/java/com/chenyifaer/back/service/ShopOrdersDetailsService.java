package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.OrdersDetailDTO;
import com.chenyifaer.back.entity.po.ShopOrdersDetailsPO;
import com.chenyifaer.back.entity.vo.OrdersDetailVO;

import java.util.List;

/**
 * 订单管理 - 订单详情表 服务类
 * @author wudh
 * @since 2019-04-25
 */
public interface ShopOrdersDetailsService extends IService<ShopOrdersDetailsPO> {

    /**
     * 查询订单详情
     * @Author:wudh
     * @Date: 2019/4/25 23:04
     */
    List<OrdersDetailVO> getOrdersDetail(OrdersDetailDTO ordersDetailDTO);

}
