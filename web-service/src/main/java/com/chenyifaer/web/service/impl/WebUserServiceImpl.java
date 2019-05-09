package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.dto.UserDTO;
import com.chenyifaer.web.entity.po.WebUserPO;
import com.chenyifaer.web.dao.WebUserDao;
import com.chenyifaer.web.entity.vo.LoginUserVO;
import com.chenyifaer.web.service.WebUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台账号管理 - 前台用户表 服务实现类
 * @author wudh
 * @since 2019-05-09
 */
@Service
public class WebUserServiceImpl extends ServiceImpl<WebUserDao, WebUserPO> implements WebUserService {

    @Autowired
    private WebUserDao webUserDao;

    @Override
    public List<LoginUserVO> getLoginUser(UserDTO userDTO) {
        return this.webUserDao.getLoginUser(userDTO);
    }
}
