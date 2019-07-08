package com.chenyifaer.app.service;

import com.chenyifaer.app.entity.dto.LoginDTO;
import com.chenyifaer.app.entity.po.AppUserPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 小程序端 - 用户表 服务类
 * </p>
 *
 * @author wudh
 * @since 2019-07-06
 */
public interface AppUserService extends IService<AppUserPO> {

    String login(LoginDTO loginDTO);

}
