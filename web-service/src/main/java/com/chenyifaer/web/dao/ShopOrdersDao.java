package com.chenyifaer.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.web.entity.dto.OrdersDTO;
import com.chenyifaer.web.entity.po.ShopOrdersPO;
import com.chenyifaer.web.entity.vo.UserOrdersVO;

import java.util.List;

/**
 * 订单管理 - 订单表 Mapper 接口
 * @author wudh
 * @since 2019-05-13
 */
public interface ShopOrdersDao extends BaseMapper<ShopOrdersPO> {

    /**
     * 查询用户订单
     * @Author:wudh
     * @Date: 2019/5/21 12:11
     */
    List<UserOrdersVO> getList(OrdersDTO ordersDTO);

}
