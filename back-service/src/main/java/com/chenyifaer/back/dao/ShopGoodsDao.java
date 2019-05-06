package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.dto.GoodsDTO;
import com.chenyifaer.back.entity.po.ShopGoodsPO;
import com.chenyifaer.back.entity.vo.GoodsDetailVO;
import com.chenyifaer.back.entity.vo.GoodsVO;

import java.util.List;

/**
 * 商品管理 - 商品表 Mapper 接口
 * @author wudh
 * @since 2019-04-25
 */
public interface ShopGoodsDao extends BaseMapper<ShopGoodsPO> {

    /**
     * 查询商品列表
     * @Author:wudh
     * @Date: 2019/5/4 19:35
     */
    List<GoodsVO> getList(GoodsDTO goodsDTO);

    /**
     * 查询商品详情
     * @Author:wudh
     * @Date: 2019/5/6 11:33
     */
    List<GoodsDetailVO> getDetail(GoodsDTO goodsDTO);

}
