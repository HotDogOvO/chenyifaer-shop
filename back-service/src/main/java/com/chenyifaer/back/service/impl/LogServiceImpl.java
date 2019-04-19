package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.LogDao;
import com.chenyifaer.back.entity.dto.LogDTO;
import com.chenyifaer.back.entity.po.LogPO;
import com.chenyifaer.back.entity.vo.LogActionVO;
import com.chenyifaer.back.entity.vo.LogVO;
import com.chenyifaer.back.service.LogService;
import com.chenyifaer.back.util.ExportPOIUtils;
import com.chenyifaer.basic.common.exception.ExportException;
import com.chenyifaer.basic.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 系统管理 - 系统日志表 服务实现类
 * @author wudh
 * @since 2019-04-07
 */

@Slf4j
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, LogPO> implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public List<LogVO> getList(LogDTO logDTO) {
        return this.logDao.getList(logDTO);
    }

    @Override
    public List<LogActionVO> getAction() {
        return this.logDao.getAction();
    }

    @Override
    public void export(LogDTO logDTO, HttpServletResponse response) throws ExportException {
        try {
            List<LogVO> list = this.logDao.getList(logDTO);

            String[] columnNames = {"操作时间", "操作人", "菜单名", "动作", "操作"};
            String[] keys = {"createTime", "adminUserName", "menuName", "action", "operation"};
            ExportPOIUtils.start_download(response, "日志列表" + DateUtil.getTime().toString(), list, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("function error WebUserServiceImpl - export 用户列表导出失败，失败原因是：{}",e);
        }
    }
}
