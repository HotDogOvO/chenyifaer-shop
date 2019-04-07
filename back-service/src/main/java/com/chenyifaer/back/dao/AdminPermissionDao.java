package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.po.AdminPermissionPO;
import com.chenyifaer.back.entity.vo.AdminPermissionMenuVO;

import java.util.List;

/**
 * 权限管理 - 后台权限表 Mapper 接口
 * @author wudh
 * @since 2019-04-06
 */
public interface AdminPermissionDao extends BaseMapper<AdminPermissionPO> {

    /**
     * 查询权限列表
     * @Author:wudh
     * @Date: 2019/4/7 17:31
     */
    List<AdminPermissionMenuVO> getList();

}
