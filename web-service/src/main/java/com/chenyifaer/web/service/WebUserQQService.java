package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.dto.UserQQDTO;
import com.chenyifaer.web.entity.po.WebUserQQPO;
import com.chenyifaer.web.entity.vo.LoginUserVO;

import java.util.List;

/**
 * <p>
 * 前台账号管理 - QQ用户信息表 服务类
 * </p>
 *
 * @author wudh
 * @since 2019-07-29
 */
public interface WebUserQQService extends IService<WebUserQQPO> {

    /**
     * 查询QQ用户的登录信息
     * @Author:wudh
     * @Date: 2019/7/30 10:49
     */
    List<LoginUserVO> getQQLoginUser(UserQQDTO userQQDTO);

}
