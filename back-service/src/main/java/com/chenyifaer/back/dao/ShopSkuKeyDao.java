package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.dto.SkuKeyDTO;
import com.chenyifaer.back.entity.po.ShopSkuKeyPO;
import com.chenyifaer.back.entity.vo.SkuKeyVO;

import java.util.List;

/**
 * 分类管理 - sku分类表 Mapper 接口
 * @author wudh
 * @since 2019-04-30
 */
public interface ShopSkuKeyDao extends BaseMapper<ShopSkuKeyPO> {

    /**
     * 查询SKU列表
     * @Author:wudh
     * @Date: 2019/4/30 13:35
     */
    List<SkuKeyVO> getList(SkuKeyDTO skuKeyDTO);

}
