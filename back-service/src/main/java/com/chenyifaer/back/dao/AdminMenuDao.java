package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.po.AdminMenuPO;
import com.chenyifaer.back.entity.vo.AdminMenuVO;

import java.util.List;

/**
 * 权限管理 - 后台菜单表 Mapper 接口
 * @author wudh
 * @since 2019-04-06
 */
public interface AdminMenuDao extends BaseMapper<AdminMenuPO> {

    /**
     * 查询菜单列表
     * @Author:wudh
     * @Date: 2019/4/6 23:19
     */
    List<AdminMenuVO> getList();

    /**
     * 查询菜单下是否存在有子菜单
     * @Author:wudh
     * @Date: 2019/4/7 19:38
     */
    int getCount(int adminMenuId);

}
