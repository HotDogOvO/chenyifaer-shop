package com.chenyifaer.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.web.entity.dto.UserQQDTO;
import com.chenyifaer.web.entity.po.WebUserQQPO;
import com.chenyifaer.web.entity.vo.LoginUserVO;

import java.util.List;

/**
 * <p>
 * 前台账号管理 - QQ用户信息表 Mapper 接口
 * </p>
 *
 * @author wudh
 * @since 2019-07-29
 */
public interface WebUserQQDao extends BaseMapper<WebUserQQPO> {

    /**
     * 查询QQ用户的登录信息
     * @Author:wudh
     * @Date: 2019/7/30 10:49
     */
    List<LoginUserVO> getQQLoginUser(UserQQDTO userQQDTO);
}
