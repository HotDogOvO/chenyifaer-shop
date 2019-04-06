package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.po.AdminUserPO;
import com.chenyifaer.back.dao.AdminUserDao;
import com.chenyifaer.back.service.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号管理 - 后台账号表 服务实现类
 * @author wudh
 * @since 2019-04-06
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUserPO> implements AdminUserService {

}
