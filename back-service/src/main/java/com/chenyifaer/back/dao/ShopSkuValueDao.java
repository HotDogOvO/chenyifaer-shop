package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.dto.SkuValueDTO;
import com.chenyifaer.back.entity.po.ShopSkuValuePO;
import com.chenyifaer.back.entity.vo.SkuValueTreeVO;
import com.chenyifaer.back.entity.vo.SkuValueVO;

import java.util.List;

/**
 * 分类管理 - sku属性表 Mapper 接口
 * @author wudh
 * @since 2019-04-30
 */
public interface ShopSkuValueDao extends BaseMapper<ShopSkuValuePO> {

    /**
     * 查询属性列表
     * @Author:wudh
     * @Date: 2019/4/30 15:26
     */
    List<SkuValueVO> getList(SkuValueDTO skuValueDTO);

    /**
     * 查询属性树状图
     * @Author:wudh
     * @Date: 2019/4/30 17:48
     */
    List<SkuValueTreeVO> getTreeList();
}
