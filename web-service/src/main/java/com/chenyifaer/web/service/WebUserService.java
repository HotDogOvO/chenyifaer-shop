package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.dto.UserDTO;
import com.chenyifaer.web.entity.po.WebUserPO;
import com.chenyifaer.web.entity.vo.LoginUserVO;

import java.util.List;

/**
 * 前台账号管理 - 前台用户表 服务类
 * @author wudh
 * @since 2019-05-09
 */
public interface WebUserService extends IService<WebUserPO> {

    /**
     * 获取登录用户信息
     * @Author:wudh
     * @Date: 2019/5/9 17:40
     */
    List<LoginUserVO> getLoginUser(UserDTO userDTO);

}
