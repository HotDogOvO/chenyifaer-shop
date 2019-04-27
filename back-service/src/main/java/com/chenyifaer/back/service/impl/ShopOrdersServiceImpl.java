package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.ShopOrdersDao;
import com.chenyifaer.back.entity.dto.OrdersDTO;
import com.chenyifaer.back.entity.po.ShopOrdersPO;
import com.chenyifaer.back.entity.vo.OrdersVO;
import com.chenyifaer.back.enums.OrdersCouponsStatusEnum;
import com.chenyifaer.back.enums.OrdersIntegralStatusEnum;
import com.chenyifaer.back.enums.OrdersStatusEnum;
import com.chenyifaer.back.service.ShopOrdersService;
import com.chenyifaer.back.util.ExportPOIUtils;
import com.chenyifaer.basic.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单管理 - 订单表 服务实现类
 * @author wudh
 * @since 2019-04-25
 */

@Slf4j
@Service
public class ShopOrdersServiceImpl extends ServiceImpl<ShopOrdersDao, ShopOrdersPO> implements ShopOrdersService {

    @Autowired
    private ShopOrdersDao shopOrdersDao;

    @Override
    public List<OrdersVO> getList(OrdersDTO ordersDTO) {
        return this.shopOrdersDao.getList(ordersDTO);
    }

    @Override
    public void export(OrdersDTO ordersDTO, HttpServletResponse response) {
        try {
            List<OrdersVO> list = this.shopOrdersDao.getList(ordersDTO);

            list.stream().map(x -> {
                //订单状态
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_000.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_000.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_001.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_001.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_002.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_002.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_003.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_003.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_004.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_004.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_007.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_007.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_008.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_008.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_009.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_009.getMsg());
                }
                if (x.getStatus().equals(OrdersStatusEnum.STATUS_099.getCode())) {
                    x.setStatus(OrdersStatusEnum.STATUS_099.getMsg());
                }

                //是否优惠券付款
                /** 替换0为否 */
                if (x.getCouponsStatus().equals(OrdersCouponsStatusEnum.FALSE.getCode())) {
                    x.setCouponsStatus(OrdersCouponsStatusEnum.FALSE.getMsg());
                }
                /** 替换1为是 */
                if (x.getCouponsStatus().equals(OrdersCouponsStatusEnum.TRUE.getCode())) {
                    x.setCouponsStatus(OrdersCouponsStatusEnum.TRUE.getMsg());
                }

                //是否积分付款
                /** 替换0为否 */
                if (x.getIntegralStatus().equals(OrdersIntegralStatusEnum.FALSE.getCode())) {
                    x.setIntegralStatus(OrdersIntegralStatusEnum.FALSE.getMsg());
                }
                /** 替换1为是 */
                if (x.getIntegralStatus().equals(OrdersIntegralStatusEnum.TRUE.getCode())) {
                    x.setIntegralStatus(OrdersIntegralStatusEnum.TRUE.getMsg());
                }
                return x;
            }).collect(Collectors.toList());

            String[] columnNames = {"订单名", "收货人", "订单流水号", "支付流水号", "快递单号" , "商品价格" , "运费", "订单总格", "订单商品总数", "订单状态", "是否为积分付款", "是否为优惠券付款", "创建时间"};
            String[] keys = {"ordersName", "consigneeName", "flowNumber", "payFlowNumber", "expressNumber", "goodsPrice", "expressPrice", "ordersPrice", "ordersCount", "status", "integralStatus", "couponsStatus", "createTime"};
            ExportPOIUtils.start_download(response, "订单列表" + DateUtil.getTime().toString(), list, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【ERROR】 - function  ShopOrdersServiceImpl - export 订单列表导出失败，失败原因是：{}",e);
        }
    }
}
