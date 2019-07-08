package com.chenyifaer.app.service.impl;

import com.chenyifaer.app.constant.AppConstant;
import com.chenyifaer.app.entity.dto.NewsDTO;
import com.chenyifaer.app.entity.po.AppNewsPO;
import com.chenyifaer.app.dao.AppNewsDao;
import com.chenyifaer.app.entity.vo.NewsDetailVO;
import com.chenyifaer.app.entity.vo.NewsVO;
import com.chenyifaer.app.enums.ImgTypeEnum;
import com.chenyifaer.app.service.AppNewsService;
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
 * @since 2019-07-08
 */
@Service
public class AppNewsServiceImpl extends ServiceImpl<AppNewsDao, AppNewsPO> implements AppNewsService {

    @Autowired
    private AppNewsDao appNewsDao;

    @Override
    public List<NewsVO> getList() {
        return this.appNewsDao.getList(new NewsDTO().setImgType(ImgTypeEnum.IMG_TYPE_001.getCode()));
    }

    @Override
    public List<NewsDetailVO> getDetail(NewsDTO newsDTO) {
        return this.appNewsDao.getDetail(newsDTO.setImgType(ImgTypeEnum.IMG_TYPE_002.getCode()));
    }

    @Override
    public List<NewsVO> getIndexList() {
        return this.appNewsDao.getList(new NewsDTO()
                .setImgType(ImgTypeEnum.IMG_TYPE_001.getCode())
                .setStartSize(AppConstant.START_SIZE)
                .setEndSize(AppConstant.END_SIZE));
    }
}
