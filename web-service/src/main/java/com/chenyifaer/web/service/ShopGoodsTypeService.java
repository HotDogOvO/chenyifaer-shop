package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.dto.GoodsTypeDTO;
import com.chenyifaer.web.entity.po.ShopGoodsTypePO;
import com.chenyifaer.web.entity.vo.GoodsTypeThreeRankVO;
import com.chenyifaer.web.entity.vo.GoodsTypeVO;

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
 * 分类管理 - 商品分类表 服务类
 * @author wudh
 * @since 2019-05-07
 */
public interface ShopGoodsTypeService extends IService<ShopGoodsTypePO> {

    /**
     * 查询商城首页 - 商品分类选项卡
     * @Author:wudh
     * @Date: 2019/5/7 21:38
     */
    List<GoodsTypeVO> getList();

    /**
     * 查询商品三级分类
     * @Author:wudh
     * @Date: 2019/5/8 17:37
     */
    List<GoodsTypeThreeRankVO> getThreeRankTypeByTypeId(GoodsTypeDTO goodsTypeDTO);

}
