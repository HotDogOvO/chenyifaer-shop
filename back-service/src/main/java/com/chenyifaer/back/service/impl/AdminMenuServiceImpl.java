package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.AdminMenuDao;
import com.chenyifaer.back.entity.po.AdminMenuPO;
import com.chenyifaer.back.entity.vo.AdminMenuVO;
import com.chenyifaer.back.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限管理 - 后台菜单表 服务实现类
 * @author wudh
 * @since 2019-04-06
 */
@Service
public class AdminMenuServiceImpl extends ServiceImpl<AdminMenuDao, AdminMenuPO> implements AdminMenuService {

    @Autowired
    private AdminMenuDao adminMenuDao;

    @Override
    public List<AdminMenuVO> getList() {
        return this.adminMenuDao.getList();
    }

    @Override
    public int getCount(int adminMenuId) {
        return this.adminMenuDao.getCount(adminMenuId);
    }
}
