package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.dto.UserQQDTO;
import com.chenyifaer.web.entity.po.WebUserQQPO;
import com.chenyifaer.web.dao.WebUserQQDao;
import com.chenyifaer.web.entity.vo.LoginUserVO;
import com.chenyifaer.web.service.WebUserQQService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 前台账号管理 - QQ用户信息表 服务实现类
 * </p>
 *
 * @author wudh
 * @since 2019-07-29
 */
@Service
public class WebUserQQServiceImpl extends ServiceImpl<WebUserQQDao, WebUserQQPO> implements WebUserQQService {

    @Autowired
    private WebUserQQDao webUserQQDao;

    @Override
    public List<LoginUserVO> getQQLoginUser(UserQQDTO userQQDTO) {
        return this.webUserQQDao.getQQLoginUser(userQQDTO);
    }
}
