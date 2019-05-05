package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.GoodsCheckDTO;
import com.chenyifaer.back.entity.po.ShopGoodsCheckPO;
import com.chenyifaer.back.dao.ShopGoodsCheckDao;
import com.chenyifaer.back.entity.vo.GoodsCheckVO;
import com.chenyifaer.back.service.ShopGoodsCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理 - 商品审核表 服务实现类
 * @author wudh
 * @since 2019-05-05
 */
@Service
public class ShopGoodsCheckServiceImpl extends ServiceImpl<ShopGoodsCheckDao, ShopGoodsCheckPO> implements ShopGoodsCheckService {

    @Autowired
    private ShopGoodsCheckDao shopGoodsCheckDao;

    @Override
    public List<GoodsCheckVO> getList(GoodsCheckDTO goodsCheckDTO) {
        return this.shopGoodsCheckDao.getList(goodsCheckDTO);
    }
}
