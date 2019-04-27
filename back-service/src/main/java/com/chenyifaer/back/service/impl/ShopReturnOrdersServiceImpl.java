package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.ShopReturnOrdersDao;
import com.chenyifaer.back.entity.dto.ReturnOrdersDTO;
import com.chenyifaer.back.entity.po.ShopReturnOrdersPO;
import com.chenyifaer.back.entity.vo.ReturnOrdersDetailVO;
import com.chenyifaer.back.entity.vo.ReturnOrdersVO;
import com.chenyifaer.back.enums.ReturnStatusEnum;
import com.chenyifaer.back.enums.ReturnTypeEnum;
import com.chenyifaer.back.enums.ReturnGoodsTypeEnum;
import com.chenyifaer.back.service.ShopReturnOrdersService;
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
 * 订单管理 - 退单表 服务实现类
 * @author wudh
 * @since 2019-04-26
 */

@Slf4j
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

    @Override
    public void export(ReturnOrdersDTO returnOrdersDTO, HttpServletResponse response) {
        try {
            List<ReturnOrdersVO> list = this.shopReturnOrdersDao.getList(returnOrdersDTO);

            list.stream().map(x -> {
                //退货状态
                if (x.getStatus().equals(ReturnStatusEnum.RETURN_STATUS_000.getCode())) {
                    x.setStatus(ReturnStatusEnum.RETURN_STATUS_000.getMsg());
                }
                if (x.getStatus().equals(ReturnStatusEnum.RETURN_STATUS_001.getCode())) {
                    x.setStatus(ReturnStatusEnum.RETURN_STATUS_001.getMsg());
                }
                if (x.getStatus().equals(ReturnStatusEnum.RETURN_STATUS_002.getCode())) {
                    x.setStatus(ReturnStatusEnum.RETURN_STATUS_002.getMsg());
                }
                if (x.getStatus().equals(ReturnStatusEnum.RETURN_STATUS_003.getCode())) {
                    x.setStatus(ReturnStatusEnum.RETURN_STATUS_003.getMsg());
                }
                if (x.getStatus().equals(ReturnStatusEnum.RETURN_STATUS_004.getCode())) {
                    x.setStatus(ReturnStatusEnum.RETURN_STATUS_004.getMsg());
                }

                //退货类型
                /** 替换1为退货退款 */
                if (x.getReturnType().equals(ReturnTypeEnum.RETURN_TYPE_001.getCode())) {
                    x.setReturnType(ReturnTypeEnum.RETURN_TYPE_001.getMsg());
                }
                /** 替换2为仅退款 */
                if (x.getReturnType().equals(ReturnTypeEnum.RETURN_TYPE_002.getCode())) {
                    x.setReturnType(ReturnTypeEnum.RETURN_TYPE_002.getMsg());
                }

                //退货商品类型
                /** 替换1为已收到货 */
                if (x.getGoodsType().equals(ReturnGoodsTypeEnum.RETURN_GOODS_TYPE_001.getCode())) {
                    x.setGoodsType(ReturnGoodsTypeEnum.RETURN_GOODS_TYPE_001.getMsg());
                }
                /** 替换2为未收到货 */
                if (x.getGoodsType().equals(ReturnGoodsTypeEnum.RETURN_GOODS_TYPE_002.getCode())) {
                    x.setGoodsType(ReturnGoodsTypeEnum.RETURN_GOODS_TYPE_002.getMsg());
                }
                return x;
            }).collect(Collectors.toList());

            String[] columnNames = {"退货商品名", "退货用户名", "订单流水号", "退单流水号", "退款金额" , "状态" , "退货类型", "商品当前状态", "退货备注", "商家审核备注", "创建时间", "审核时间", "审核失败时间"};
            String[] keys = {"goodsName", "userNickName", "flowNumber", "returnFlowNumber", "returnOrdersPrice", "status", "returnType", "goodsType", "retrunRemark", "checkFailRemark", "createTime", "checkTime", "checkFailTime"};
            ExportPOIUtils.start_download(response, "订单列表" + DateUtil.getTime().toString(), list, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【ERROR】 - function  ShopReturnOrdersServiceImpl - export 退单列表导出失败，失败原因是：{}",e);
        }
    }
}
