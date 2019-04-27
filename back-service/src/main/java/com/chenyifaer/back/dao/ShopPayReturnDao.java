package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.dto.PayReturnDTO;
import com.chenyifaer.back.entity.po.ShopPayReturnPO;
import com.chenyifaer.back.entity.vo.PayReturnVO;

import java.util.List;

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
 * 支付管理 - 退款表 Mapper 接口
 * @author wudh
 * @since 2019-04-27
 */
public interface ShopPayReturnDao extends BaseMapper<ShopPayReturnPO> {

    /**
     * 查询退款列表
     * @Author:wudh
     * @Date: 2019/4/27 20:51
     */
    List<PayReturnVO> getList(PayReturnDTO payReturnDTO);

}
