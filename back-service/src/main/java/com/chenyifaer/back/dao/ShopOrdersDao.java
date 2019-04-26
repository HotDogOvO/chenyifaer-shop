package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.dto.OrdersDTO;
import com.chenyifaer.back.entity.po.ShopOrdersPO;
import com.chenyifaer.back.entity.vo.OrdersVO;

import java.util.List;

/**
 * 订单管理 - 订单表 Mapper 接口
 * @author wudh
 * @since 2019-04-25
 */
public interface ShopOrdersDao extends BaseMapper<ShopOrdersPO> {

    /**
     * 查询订单列表
     * @Author:wudh
     * @Date: 2019/4/25 20:16
     */
    List<OrdersVO> getList(OrdersDTO ordersDTO);

}
