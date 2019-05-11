package com.chenyifaer.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.web.entity.dto.ShopCartDTO;
import com.chenyifaer.web.entity.po.ShopCartPO;
import com.chenyifaer.web.entity.vo.ShopCartVO;

import java.util.List;

/**
 * 商品管理 - 购物车表 Mapper 接口
 * @author wudh
 * @since 2019-05-11
 */
public interface ShopCartDao extends BaseMapper<ShopCartPO> {

    /**
     * 查询购物车列表
     * @Author:wudh
     * @Date: 2019/5/11 18:13
     */
    List<ShopCartVO> getList(ShopCartDTO shopCartDTO);

}
