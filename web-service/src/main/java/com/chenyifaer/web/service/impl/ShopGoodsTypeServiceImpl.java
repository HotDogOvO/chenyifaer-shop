package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.po.ShopGoodsTypePO;
import com.chenyifaer.web.dao.ShopGoodsTypeDao;
import com.chenyifaer.web.entity.vo.GoodsTypeVO;
import com.chenyifaer.web.service.ShopGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 分类管理 - 商品分类表 服务实现类
 * @author wudh
 * @since 2019-05-07
 */
@Service
public class ShopGoodsTypeServiceImpl extends ServiceImpl<ShopGoodsTypeDao, ShopGoodsTypePO> implements ShopGoodsTypeService {

    @Autowired
    private ShopGoodsTypeDao shopGoodsTypeDao;

    @Override
    public List<GoodsTypeVO> getList() {
        return this.shopGoodsTypeDao.getList();
    }
}
