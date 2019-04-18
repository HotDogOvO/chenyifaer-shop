package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.WebUserDetailDao;
import com.chenyifaer.back.entity.dto.WebUserDetailDTO;
import com.chenyifaer.back.entity.po.WebUserDetailPO;
import com.chenyifaer.back.entity.vo.WebUserDetailVO;
import com.chenyifaer.back.service.WebUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台账号管理 - 前台用户详情表 服务实现类
 * @author wudh
 * @since 2019-04-18
 */
@Service
public class WebUserDetailServiceImpl extends ServiceImpl<WebUserDetailDao, WebUserDetailPO> implements WebUserDetailService {

    @Autowired
    private WebUserDetailDao webUserDetailDao;

    @Override
    public List<WebUserDetailVO> getUserDetail(WebUserDetailDTO webUserDetailDTO) {
        return this.webUserDetailDao.getUserDetail(webUserDetailDTO);
    }
}
