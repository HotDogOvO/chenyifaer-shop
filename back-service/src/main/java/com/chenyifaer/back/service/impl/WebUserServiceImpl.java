package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.WebUserDTO;
import com.chenyifaer.back.entity.po.WebUserPO;
import com.chenyifaer.back.dao.WebUserDao;
import com.chenyifaer.back.entity.vo.WebUserVO;
import com.chenyifaer.back.service.WebUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 前台账号管理 - 前台用户表 服务实现类
 * @author wudh
 * @since 2019-04-18
 */
@Service
public class WebUserServiceImpl extends ServiceImpl<WebUserDao, WebUserPO> implements WebUserService {

    @Autowired
    private WebUserDao webUserDao;

    @Override
    public List<WebUserVO> getList(WebUserDTO webUserDTO) {
        return this.webUserDao.getList(webUserDTO);
    }
}
