package com.chenyifaer.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.back.entity.dto.WebUserDTO;
import com.chenyifaer.back.entity.po.WebUserPO;
import com.chenyifaer.back.entity.vo.WebUserVO;

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
 * 前台账号管理 - 前台用户表 服务类
 * @author wudh
 * @since 2019-04-18
 */
public interface WebUserService extends IService<WebUserPO> {

    /**
     * 查询前台用户列表
     * @Author:wudh
     * @Date: 2019/4/18 14:18
     */
    List<WebUserVO> getList(WebUserDTO webUserDTO);

}
