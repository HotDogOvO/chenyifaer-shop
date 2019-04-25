package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.ShopGoodsTypeDTO;
import com.chenyifaer.back.entity.po.ShopGoodsTypePO;
import com.chenyifaer.back.dao.ShopGoodsTypeDao;
import com.chenyifaer.back.entity.vo.ShopGoodsTypeVO;
import com.chenyifaer.back.service.ShopGoodsTypeService;
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
 * @since 2019-04-25
 */
@Service
public class ShopGoodsTypeServiceImpl extends ServiceImpl<ShopGoodsTypeDao, ShopGoodsTypePO> implements ShopGoodsTypeService {

    @Autowired
    private ShopGoodsTypeDao shopGoodsTypeDao;

    @Override
    public List<ShopGoodsTypeVO> getList(ShopGoodsTypeDTO shopGoodsTypeDTO) {
        return this.shopGoodsTypeDao.getList(shopGoodsTypeDTO);
    }
}
