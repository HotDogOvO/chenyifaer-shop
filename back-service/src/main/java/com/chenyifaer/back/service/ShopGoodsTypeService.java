package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.ShopGoodsTypeDTO;
import com.chenyifaer.back.entity.po.ShopGoodsTypePO;
import com.chenyifaer.back.entity.vo.ShopGoodsTypeVO;

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
 * @since 2019-04-25
 */
public interface ShopGoodsTypeService extends IService<ShopGoodsTypePO> {

    /**
     * 查询分类列表
     * @Author:wudh
     * @Date: 2019/4/25 11:31
     */
    List<ShopGoodsTypeVO> getList(ShopGoodsTypeDTO shopGoodsTypeDTO);

}
