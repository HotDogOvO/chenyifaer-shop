package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.po.ShopPayPO;

/**
 * 支付管理 - 支付表 服务类
 * @author wudh
 * @since 2019-05-20
 */
public interface ShopPayService extends IService<ShopPayPO> {

    /**
     * 新增支付信息
     * @Author:wudh
     * @Date: 2019/5/20 22:59
     */
    int addPay(String flowNumber, String payFlowNumber,Integer status);
}
