package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.dto.AppNewsDTO;
import com.chenyifaer.back.entity.po.AppNewsPO;
import com.chenyifaer.back.entity.vo.AppNewsVO;

import java.util.List;

/**
 * <p>
 * 小程序端 - 最新动态表 Mapper 接口
 * </p>
 *
 * @author wudh
 * @since 2019-07-12
 */
public interface AppNewsDao extends BaseMapper<AppNewsPO> {

    /**
     * 获取最新动态列表
     * @Author:wudh
     * @Date: 2019/7/12 17:06
     */
    List<AppNewsVO> getList(AppNewsDTO appNewsDTO);

}
