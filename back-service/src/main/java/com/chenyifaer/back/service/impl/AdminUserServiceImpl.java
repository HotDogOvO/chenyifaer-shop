package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.AdminUserDao;
import com.chenyifaer.back.entity.dto.AdminUserDTO;
import com.chenyifaer.back.entity.dto.OauthUserDTO;
import com.chenyifaer.back.entity.po.AdminUserPO;
import com.chenyifaer.back.entity.vo.AdminUpdateUserVO;
import com.chenyifaer.back.entity.vo.AdminUserVO;
import com.chenyifaer.back.entity.vo.OauthUserVO;
import com.chenyifaer.back.enums.AdminUserStatusEnum;
import com.chenyifaer.back.service.AdminUserService;
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
 * 账号管理 - 后台账号表 服务实现类
 * @author wudh
 * @since 2019-04-06
 */

@Slf4j
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUserPO> implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public List<AdminUserVO> getList(AdminUserDTO adminUserDTO) {
        return this.adminUserDao.getList(adminUserDTO);
    }

    @Override
    public OauthUserVO getOauthUser(OauthUserDTO oauthUserDTO) {
        return this.adminUserDao.getOauthUser(oauthUserDTO);
    }

    @Override
    public List<AdminUpdateUserVO> getUserById(AdminUserDTO adminUserDTO) {
        return this.adminUserDao.getUserById(adminUserDTO);
    }

    @Override
    public void export(AdminUserDTO adminUserDTO, HttpServletResponse response) throws ExportException {
        try {
            List<AdminUserVO> list = this.adminUserDao.getList(adminUserDTO);

            list.stream().map(x -> {
                /** 替换0为禁用 */
                if (x.getStatus().equals(AdminUserStatusEnum.DISABLE.getCode())) {
                    x.setStatus(AdminUserStatusEnum.DISABLE.getMsg());
                }
                /** 替换1为启用 */
                if (x.getStatus().equals(AdminUserStatusEnum.ENABLE.getCode())) {
                    x.setStatus(AdminUserStatusEnum.ENABLE.getMsg());
                }
                return x;
            });

            String[] columnNames = {"姓名", "账号", "角色名", "手机号", "邮箱" , "状态" , "创建时间"};
            String[] keys = {"adminUserName", "adminUserAccount", "adminRoleName", "adminUserPhone", "adminUserEmail", "status", "createTime"};
            ExportPOIUtils.start_download(response, "运营账号列表" + DateUtil.getTime().toString(), list, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【ERROR】 - function  AdminUserServiceImpl - export 运营账号列表导出失败，失败原因是：{}",e);
        }
    }

}
