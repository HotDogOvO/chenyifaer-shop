package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.AdminRoleDTO;
import com.chenyifaer.back.entity.po.AdminRolePO;
import com.chenyifaer.back.entity.vo.AdminRoleVO;

import java.util.List;

/**
 * 角色管理 - 后台角色表 服务类
 * @author wudh
 * @since 2019-04-06
 */
public interface AdminRoleService extends IService<AdminRolePO> {

    /**
     * 查询角色列表
     * @Author:wudh
     * @Date: 2019/4/7 15:51
     */
    List<AdminRoleVO> getList(AdminRoleDTO adminRoleDTO);

}
