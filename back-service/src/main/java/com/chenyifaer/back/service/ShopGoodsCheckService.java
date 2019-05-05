package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.GoodsCheckDTO;
import com.chenyifaer.back.entity.po.ShopGoodsCheckPO;
import com.chenyifaer.back.entity.vo.GoodsCheckVO;

import java.util.List;

/**
 * 商品管理 - 商品审核表 服务类
 * @author wudh
 * @since 2019-05-05
 */
public interface ShopGoodsCheckService extends IService<ShopGoodsCheckPO> {

    /**
     * 查询商品审核列表
     * @Author:wudh
     * @Date: 2019/5/5 22:52
     */
    List<GoodsCheckVO> getList(GoodsCheckDTO goodsCheckDTO);

}
