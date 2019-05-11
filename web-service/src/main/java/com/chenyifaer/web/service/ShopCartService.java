package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.dto.ShopCartDTO;
import com.chenyifaer.web.entity.po.ShopCartPO;
import com.chenyifaer.web.entity.vo.ShopCartVO;

import java.util.List;

/**
 * 商品管理 - 购物车表 服务类
 * @author wudh
 * @since 2019-05-11
 */
public interface ShopCartService extends IService<ShopCartPO> {

    /**
     * 查询购物车列表
     * @Author:wudh
     * @Date: 2019/5/11 18:13
     */
    List<ShopCartVO> getList(ShopCartDTO shopCartDTO);

}
