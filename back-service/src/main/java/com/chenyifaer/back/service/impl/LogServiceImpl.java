package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.LogDTO;
import com.chenyifaer.back.entity.po.LogPO;
import com.chenyifaer.back.dao.LogDao;
import com.chenyifaer.back.entity.vo.LogVO;
import com.chenyifaer.back.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统管理 - 系统日志表 服务实现类
 * </p>
 *
 * @author wudh
 * @since 2019-04-07
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, LogPO> implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public List<LogVO> getList(LogDTO logDTO) {
        return this.logDao.getList(logDTO);
    }
}
