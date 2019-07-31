package com.chenyifaer.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.web.entity.dto.UserWeiboDTO;
import com.chenyifaer.web.entity.po.WebUserWeiboPO;
import com.chenyifaer.web.entity.vo.LoginUserVO;

import java.util.List;

/**
 * <p>
 * 前台账号管理 - 微博用户信息表 Mapper 接口
 * </p>
 *
 * @author wudh
 * @since 2019-07-30
 */
public interface WebUserWeiboDao extends BaseMapper<WebUserWeiboPO> {

    /**
     * 查询微博登录用户的信息
     * @Author:wudh
     * @Date: 2019/7/31 10:12
     */
    List<LoginUserVO> getWeiboLoginUser(UserWeiboDTO userWeiboDTO);


}
