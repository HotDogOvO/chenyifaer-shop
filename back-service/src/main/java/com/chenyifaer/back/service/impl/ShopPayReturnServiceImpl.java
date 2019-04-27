package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.ShopPayReturnDao;
import com.chenyifaer.back.entity.dto.PayReturnDTO;
import com.chenyifaer.back.entity.po.ShopPayReturnPO;
import com.chenyifaer.back.entity.vo.PayReturnVO;
import com.chenyifaer.back.enums.PayReturnStatusEnum;
import com.chenyifaer.back.enums.PayReturnTypeEnum;
import com.chenyifaer.back.service.ShopPayReturnService;
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
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 支付管理 - 退款表 服务实现类
 * @author wudh
 * @since 2019-04-27
 */

@Slf4j
@Service
public class ShopPayReturnServiceImpl extends ServiceImpl<ShopPayReturnDao, ShopPayReturnPO> implements ShopPayReturnService {

    @Autowired
    private ShopPayReturnDao shopPayReturnDao;

    @Override
    public List<PayReturnVO> getList(PayReturnDTO payReturnDTO) {
        return this.shopPayReturnDao.getList(payReturnDTO);
    }

    @Override
    public void export(PayReturnDTO payReturnDTO, HttpServletResponse response) {
        try {
            List<PayReturnVO> list = this.shopPayReturnDao.getList(payReturnDTO);

            list.stream().map(x -> {
                //退款状态
                if (x.getStatus().equals(PayReturnStatusEnum.PAY_RETURN_STATUS_000.getCode())) {
                    x.setStatus(PayReturnStatusEnum.PAY_RETURN_STATUS_000.getMsg());
                }
                if (x.getStatus().equals(PayReturnStatusEnum.PAY_RETURN_STATUS_001.getCode())) {
                    x.setStatus(PayReturnStatusEnum.PAY_RETURN_STATUS_001.getMsg());
                }
                if (x.getStatus().equals(PayReturnStatusEnum.PAY_RETURN_STATUS_002.getCode())) {
                    x.setStatus(PayReturnStatusEnum.PAY_RETURN_STATUS_002.getMsg());
                }

                //退款类型
                /** 替换1为支付宝支付 */
                if (x.getReturnType().equals(PayReturnTypeEnum.APPLY.getCode())) {
                    x.setReturnType(PayReturnTypeEnum.APPLY.getMsg());
                }
                /** 替换2为微信支付 */
                if (x.getReturnType().equals(PayReturnTypeEnum.WECHAT.getCode())) {
                    x.setReturnType(PayReturnTypeEnum.WECHAT.getMsg());
                }
                return x;
            }).collect(Collectors.toList());

            String[] columnNames = {"订单名", "支付人", "订单流水号", "支付流水号", "退款流水号", "支付类型" , "支付金额" , "状态", "创建时间", "支付成功时间", "支付失败时间", "支付失败原因"};
            String[] keys = {"ordersName", "userNickName", "flowNumber", "payFlowNumber","returnFlowNumber", "returnType", "returnMoney", "status", "createTime", "paySuccessTime", "payFailTime", "payFailRemark"};
            ExportPOIUtils.start_download(response, "支付列表" + DateUtil.getTime().toString(), list, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【ERROR】 - function  ShopPayReturnServiceImpl - export 退款列表导出失败，失败原因是：{}",e);
        }
    }
}
