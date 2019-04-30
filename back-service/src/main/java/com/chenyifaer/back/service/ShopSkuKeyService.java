package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.SkuKeyDTO;
import com.chenyifaer.back.entity.po.ShopSkuKeyPO;
import com.chenyifaer.back.entity.vo.SkuKeyVO;

import java.util.List;

/**
 * 分类管理 - sku分类表 服务类
 * @author wudh
 * @since 2019-04-30
 */
public interface ShopSkuKeyService extends IService<ShopSkuKeyPO> {

    /**
     * 查询SKU列表
     * @Author:wudh
     * @Date: 2019/4/30 13:35
     */
    List<SkuKeyVO> getList(SkuKeyDTO skuKeyDTO);

}
