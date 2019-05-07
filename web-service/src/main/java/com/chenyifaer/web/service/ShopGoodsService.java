package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.po.ShopGoodsPO;
import com.chenyifaer.web.entity.vo.GoodsRecommendedVO;

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
 * 商品管理 - 商品表 服务类
 * @author wudh
 * @since 2019-05-07
 */
public interface ShopGoodsService extends IService<ShopGoodsPO> {

    /**
     * 查询首页推荐商品
     * @Author:wudh
     * @Date: 2019/5/7 22:11
     */
    List<GoodsRecommendedVO> getRecommendedList();

}