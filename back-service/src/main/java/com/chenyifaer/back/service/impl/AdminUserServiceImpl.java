package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.AdminUserDTO;
import com.chenyifaer.back.entity.dto.OauthUserDTO;
import com.chenyifaer.back.entity.po.AdminUserPO;
import com.chenyifaer.back.dao.AdminUserDao;
import com.chenyifaer.back.entity.vo.AdminUserVO;
import com.chenyifaer.back.entity.vo.OauthUserVO;
import com.chenyifaer.back.service.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号管理 - 后台账号表 服务实现类
 * @author wudh
 * @since 2019-04-06
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUserPO> implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public List<AdminUserVO> getList(AdminUserDTO adminUserDTO) {
        return this.adminUserDao.getList(adminUserDTO);
    }

    @Override
    public OauthUserVO getOauthUser(OauthUserDTO oauthUserDTO) {
        return this.adminUserDao.getOauthUser(oauthUserDTO);
    }
}
