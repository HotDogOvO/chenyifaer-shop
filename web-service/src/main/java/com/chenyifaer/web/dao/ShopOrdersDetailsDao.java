package com.chenyifaer.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.web.entity.dto.OrdersDetailDTO;
import com.chenyifaer.web.entity.po.ShopOrdersDetailsPO;
import com.chenyifaer.web.entity.vo.OrdersDetailVO;

import java.util.List;

/**
 * 订单管理 - 订单详情表 Mapper 接口
 * @author wudh
 * @since 2019-05-13
 */
public interface ShopOrdersDetailsDao extends BaseMapper<ShopOrdersDetailsPO> {

    /**
     * 查询订单详情
     * @Author:wudh
     * @Date: 2019/5/13 13:49
     */
    List<OrdersDetailVO> getOrdersDetail(OrdersDetailDTO ordersDetailDTO);
}
