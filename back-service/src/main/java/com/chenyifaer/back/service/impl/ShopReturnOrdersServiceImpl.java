package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.ReturnOrdersDTO;
import com.chenyifaer.back.entity.po.ShopReturnOrdersPO;
import com.chenyifaer.back.dao.ShopReturnOrdersDao;
import com.chenyifaer.back.entity.vo.ReturnOrdersDetailVO;
import com.chenyifaer.back.entity.vo.ReturnOrdersVO;
import com.chenyifaer.back.service.ShopReturnOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理 - 退单表 服务实现类
 * @author wudh
 * @since 2019-04-26
 */
@Service
public class ShopReturnOrdersServiceImpl extends ServiceImpl<ShopReturnOrdersDao, ShopReturnOrdersPO> implements ShopReturnOrdersService {

    @Autowired
    private ShopReturnOrdersDao shopReturnOrdersDao;

    @Override
    public List<ReturnOrdersVO> getList(ReturnOrdersDTO returnOrdersDTO) {
        return this.shopReturnOrdersDao.getList(returnOrdersDTO);
    }

    @Override
    public List<ReturnOrdersDetailVO> getDetail(ReturnOrdersDTO returnOrdersDTO) {
        return this.shopReturnOrdersDao.getDetail(returnOrdersDTO);
    }
}
