package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.WebUserDetailDTO;
import com.chenyifaer.back.entity.po.WebUserDetailPO;
import com.chenyifaer.back.entity.vo.WebUserDetailVO;

import java.util.List;

/**
 * 前台账号管理 - 前台用户详情表 服务类
 * @author wudh
 * @since 2019-04-18
 */
public interface WebUserDetailService extends IService<WebUserDetailPO> {

    /**
     * 根據用戶ID查詢用戶詳情
     * @Author:wudh
     * @Date: 2019/4/18 15:47
     */
    List<WebUserDetailVO> getUserDetail(WebUserDetailDTO webUserDetailDTO);


}
