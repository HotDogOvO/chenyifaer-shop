package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.ReturnOrdersDTO;
import com.chenyifaer.back.entity.po.ShopReturnOrdersPO;
import com.chenyifaer.back.entity.vo.ReturnOrdersDetailVO;
import com.chenyifaer.back.entity.vo.ReturnOrdersVO;

import javax.servlet.http.HttpServletResponse;
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
 * 订单管理 - 退单表 服务类
 * @author wudh
 * @since 2019-04-26
 */
public interface ShopReturnOrdersService extends IService<ShopReturnOrdersPO> {

    /**
     * 查詢退單列表
     * @Author:wudh
     * @Date: 2019/4/26 12:03
     */
    List<ReturnOrdersVO> getList(ReturnOrdersDTO returnOrdersDTO);

    /**
     * 查询退单详情
     * @Author:wudh
     * @Date: 2019/4/26 12:46
     */
    List<ReturnOrdersDetailVO> getDetail(ReturnOrdersDTO returnOrdersDTO);

    /**
     * 导出退单列表
     * @Author:wudh
     * @Date: 2019/4/27 13:28
     */
    void export(ReturnOrdersDTO returnOrdersDTO , HttpServletResponse response);
}
