package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.AdminRoleDTO;
import com.chenyifaer.back.entity.po.AdminRolePO;
import com.chenyifaer.back.dao.AdminRoleDao;
import com.chenyifaer.back.entity.vo.AdminRoleVO;
import com.chenyifaer.back.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色管理 - 后台角色表 服务实现类
 * @author wudh
 * @since 2019-04-06
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleDao, AdminRolePO> implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    public List<AdminRoleVO> getList(AdminRoleDTO adminRoleDTO) {
        return this.adminRoleDao.getList(adminRoleDTO);
    }
}
