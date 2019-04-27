package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.ShopPayDao;
import com.chenyifaer.back.entity.dto.PayDTO;
import com.chenyifaer.back.entity.po.ShopPayPO;
import com.chenyifaer.back.entity.vo.PayVO;
import com.chenyifaer.back.enums.PayStatusEnum;
import com.chenyifaer.back.enums.PayTypeEnum;
import com.chenyifaer.back.service.ShopPayService;
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
 * 支付管理 - 支付表 服务实现类
 * @author wudh
 * @since 2019-04-25
 */

@Slf4j
@Service
public class ShopPayServiceImpl extends ServiceImpl<ShopPayDao, ShopPayPO> implements ShopPayService {

    @Autowired
    private ShopPayDao shopPayDao;

    @Override
    public List<PayVO> getList(PayDTO payDTO) {
        return this.shopPayDao.getList(payDTO);
    }

    @Override
    public void export(PayDTO payDTO, HttpServletResponse response) {
        try {
            List<PayVO> list = this.shopPayDao.getList(payDTO);

            list.stream().map(x -> {
                //支付状态
                if (x.getStatus().equals(PayStatusEnum.PAY_STATUS_000.getCode())) {
                    x.setStatus(PayStatusEnum.PAY_STATUS_000.getMsg());
                }
                if (x.getStatus().equals(PayStatusEnum.PAY_STATUS_001.getCode())) {
                    x.setStatus(PayStatusEnum.PAY_STATUS_001.getMsg());
                }
                if (x.getStatus().equals(PayStatusEnum.PAY_STATUS_008.getCode())) {
                    x.setStatus(PayStatusEnum.PAY_STATUS_008.getMsg());
                }
                if (x.getStatus().equals(PayStatusEnum.PAY_STATUS_009.getCode())) {
                    x.setStatus(PayStatusEnum.PAY_STATUS_009.getMsg());
                }

                //支付类型
                /** 替换1为支付宝支付 */
                if (x.getPayType().equals(PayTypeEnum.APPLY.getCode())) {
                    x.setPayType(PayTypeEnum.APPLY.getMsg());
                }
                /** 替换2为微信支付 */
                if (x.getPayType().equals(PayTypeEnum.WECHAT.getCode())) {
                    x.setPayType(PayTypeEnum.WECHAT.getMsg());
                }
                return x;
            }).collect(Collectors.toList());

            String[] columnNames = {"订单名", "支付人", "订单流水号", "支付流水号", "支付类型" , "支付金额" , "状态", "创建时间", "支付成功时间", "支付失败时间", "支付失败原因"};
            String[] keys = {"ordersName", "userNickName", "flowNumber", "payFlowNumber", "payType", "payMoney", "status", "createTime", "paySuccessTime", "payFailTime", "payFailRemark"};
            ExportPOIUtils.start_download(response, "支付列表" + DateUtil.getTime().toString(), list, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【ERROR】 - function  ShopPayServiceImpl - export 支付列表导出失败，失败原因是：{}",e);
        }
    }
}
