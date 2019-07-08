package com.chenyifaer.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.app.entity.dto.NewsDTO;
import com.chenyifaer.app.entity.po.AppNewsPO;
import com.chenyifaer.app.entity.vo.NewsDetailVO;
import com.chenyifaer.app.entity.vo.NewsVO;

import java.util.List;

/**
 * <p>
 * 小程序端 - 最新动态表 服务类
 * </p>
 *
 * @author wudh
 * @since 2019-07-08
 */
public interface AppNewsService extends IService<AppNewsPO> {

    /**
     * 获取最新动态列表
     * @Author:wudh
     * @Date: 2019/7/8 10:17
     */
    List<NewsVO> getList();

    /**
     * 根据主键获取最新动态详情
     * @Author:wudh
     * @Date: 2019/7/8 10:31
     */
    List<NewsDetailVO> getDetail(NewsDTO newsDTO);
}
