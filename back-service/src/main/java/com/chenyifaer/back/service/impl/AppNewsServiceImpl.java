package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.AppNewsDTO;
import com.chenyifaer.back.entity.po.AppNewsPO;
import com.chenyifaer.back.dao.AppNewsDao;
import com.chenyifaer.back.entity.vo.AppNewsVO;
import com.chenyifaer.back.service.AppNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 小程序端 - 最新动态表 服务实现类
 * </p>
 *
 * @author wudh
 * @since 2019-07-12
 */
@Service
public class AppNewsServiceImpl extends ServiceImpl<AppNewsDao, AppNewsPO> implements AppNewsService {

    @Autowired
    private AppNewsDao appNewsDao;

    @Override
    public List<AppNewsVO> getList(AppNewsDTO appNewsDTO) {
        return this.appNewsDao.getList(appNewsDTO);
    }
}
