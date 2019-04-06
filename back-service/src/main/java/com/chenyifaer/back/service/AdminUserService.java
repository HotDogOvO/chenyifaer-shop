package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.AdminUserDTO;
import com.chenyifaer.back.entity.po.AdminUserPO;
import com.chenyifaer.back.entity.vo.AdminUserVO;

import java.util.List;

/**
 * 账号管理 - 后台账号表 服务类
 * @author wudh
 * @since 2019-04-06
 */
public interface AdminUserService extends IService<AdminUserPO> {

    /**
     * 查询后台用户列表
     * @Author:wudh
     * @Date: 2019/4/6 18:18
     */
    List<AdminUserVO> getList(AdminUserDTO adminUserDTO);

}
