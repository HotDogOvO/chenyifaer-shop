package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.AdminUserMenuDTO;
import com.chenyifaer.back.entity.po.AdminPermissionPO;
import com.chenyifaer.back.dao.AdminPermissionDao;
import com.chenyifaer.back.entity.vo.AdminPermissionMenuVO;
import com.chenyifaer.back.entity.vo.AdminUserMenuVO;
import com.chenyifaer.back.service.AdminPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限管理 - 后台权限表 服务实现类
 * @author wudh
 * @since 2019-04-06
 */
@Service
public class AdminPermissionServiceImpl extends ServiceImpl<AdminPermissionDao, AdminPermissionPO> implements AdminPermissionService {

    @Autowired
    private AdminPermissionDao adminPermissionDao;

    @Override
    public List<AdminPermissionMenuVO> getList() {
        return this.adminPermissionDao.getList();
    }

    @Override
    public List<AdminUserMenuVO> getUserMenuList(AdminUserMenuDTO adminUserMenuDTO) {
        return this.adminPermissionDao.getUserMenuList(adminUserMenuDTO);
    }
}
