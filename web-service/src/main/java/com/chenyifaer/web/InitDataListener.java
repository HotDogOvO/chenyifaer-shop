package com.chenyifaer.web;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.chenyifaer.web.dao.ShopGoodsDao;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.vo.GoodsVO;
import com.chenyifaer.web.enums.GoodsImgEnum;
import com.chenyifaer.web.redis.RedisService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 项目启动初始化数据
 * @Author:wudh
 * @Date: 2019/5/12 10:02
 */

@Service
public class InitDataListener implements InitializingBean, ServletContextAware {

    @Autowired
    private ShopGoodsDao shopGoodsDao;

    @Autowired
    private RedisService redisService;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        //设置商品信息
        this.setGoods();
    }

    /**
     * 初始化商品信息
     * @Author:wudh
     * @Date: 2019/5/12 10:14
     */
    public void setGoods(){
        //获取商品信息
        List<GoodsVO> list = this.shopGoodsDao.getList(new GoodsDTO().setType(GoodsImgEnum.IMG_TYPE_001.getCode()));
        //插入Redis
        this.redisService.addGoods(list);
    }

}
