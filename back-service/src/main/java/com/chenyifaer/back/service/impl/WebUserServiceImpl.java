package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.WebUserDao;
import com.chenyifaer.back.entity.dto.WebUserDTO;
import com.chenyifaer.back.entity.po.WebUserPO;
import com.chenyifaer.back.entity.vo.WebUserVO;
import com.chenyifaer.back.enums.UserSexEnum;
import com.chenyifaer.back.enums.UserStatusEnum;
import com.chenyifaer.back.enums.UserTypeEnum;
import com.chenyifaer.back.service.WebUserService;
import com.chenyifaer.back.util.ExportPOIUtils;
import com.chenyifaer.basic.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

/**
 * 前台账号管理 - 前台用户表 服务实现类
 * @author wudh
 * @since 2019-04-18
 */
@Slf4j
@Service
public class WebUserServiceImpl extends ServiceImpl<WebUserDao, WebUserPO> implements WebUserService {

    @Autowired
    private WebUserDao webUserDao;

    @Override
    public List<WebUserVO> getList(WebUserDTO webUserDTO) {
        return this.webUserDao.getList(webUserDTO);
    }

    @Override
    public void export(WebUserDTO webUserDTO, HttpServletResponse response) {
        try {
            List<WebUserVO> list = this.webUserDao.getList(webUserDTO);

            list.stream().map(x -> {
                //性别等于0，替换为保密
                if (x.getUserSex().equals(UserSexEnum.SECRET.getCode())) {
                    x.setUserSex(UserSexEnum.SECRET.getMsg());
                }
                //性别等于1，替换为男
                if (x.getUserSex().equals(UserSexEnum.MAN.getCode())) {
                    x.setUserSex(UserSexEnum.MAN.getMsg());
                }
                //性别等于2，替换为女
                if (x.getUserSex().equals(UserSexEnum.WOMAN.getCode())) {
                    x.setUserSex(UserSexEnum.WOMAN.getMsg());
                }

                //状态等于0，替换为禁用
                if (x.getStatus().equals(UserStatusEnum.DISABLE.getCode())) {
                    x.setStatus(UserStatusEnum.DISABLE.getMsg());
                }
                //状态等于1，替换为启用
                if (x.getStatus().equals(UserStatusEnum.ENABLE.getCode())) {
                    x.setStatus(UserStatusEnum.ENABLE.getMsg());
                }
                //状态等于9，替换为注销
                if (x.getStatus().equals(UserStatusEnum.CANCELL.getCode())) {
                    x.setStatus(UserStatusEnum.CANCELL.getMsg());
                }

                //用户渠道等于1，替换为注册用户
                if (x.getUserType().equals(UserTypeEnum.REGISTER.getCode())) {
                    x.setUserType(UserTypeEnum.REGISTER.getMsg());
                }
                //用户渠道等于2，替换为微信用户
                if (x.getUserType().equals(UserTypeEnum.WECHAT.getCode())) {
                    x.setUserType(UserTypeEnum.WECHAT.getMsg());
                }
                //用户渠道等于3，替换为QQ用户
                if (x.getUserType().equals(UserTypeEnum.QQ.getCode())) {
                    x.setUserType(UserTypeEnum.QQ.getMsg());
                }
                //用户渠道等于4，替换为支付宝用户
                if (x.getUserType().equals(UserTypeEnum.APPLY.getCode())) {
                    x.setUserType(UserTypeEnum.APPLY.getMsg());
                }
                //用户渠道等于5，替换为微博用户
                if (x.getUserType().equals(UserTypeEnum.WEIBO.getCode())) {
                    x.setUserType(UserTypeEnum.WEIBO.getMsg());
                }
                return x;
            }).collect(Collectors.toList());
            String[] columnNames = {"账号", "昵称", "角色名", "性别", "用户渠道", "状态", "创建时间"};
            String[] keys = {"userAccount", "userNickName", "roleName", "userSex", "userType", "status", "createTime"};
            ExportPOIUtils.start_download(response, "用户列表" + DateUtil.getTime().toString(), list, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("function error WebUserServiceImpl - export 用户列表导出失败，失败原因是：{}",e);
        }
    }
}
