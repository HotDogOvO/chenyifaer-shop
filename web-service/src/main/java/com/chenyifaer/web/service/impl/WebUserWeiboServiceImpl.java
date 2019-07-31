package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.dto.UserWeiboDTO;
import com.chenyifaer.web.entity.po.WebUserWeiboPO;
import com.chenyifaer.web.dao.WebUserWeiboDao;
import com.chenyifaer.web.entity.vo.LoginUserVO;
import com.chenyifaer.web.service.WebUserWeiboService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 前台账号管理 - 微博用户信息表 服务实现类
 * </p>
 *
 * @author wudh
 * @since 2019-07-30
 */
@Service
public class WebUserWeiboServiceImpl extends ServiceImpl<WebUserWeiboDao, WebUserWeiboPO> implements WebUserWeiboService {

    @Autowired
    private WebUserWeiboDao webUserWeiboDao;

    @Override
    public List<LoginUserVO> getWeiboLoginUser(UserWeiboDTO userWeiboDTO) {
        return this.webUserWeiboDao.getWeiboLoginUser(userWeiboDTO);
    }
}
