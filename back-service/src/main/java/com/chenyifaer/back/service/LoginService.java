package com.chenyifaer.back.service;

import com.chenyifaer.back.entity.dto.AdminLoginDTO;

/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

/**
 * 系统管理 - 登录 服务类
 * @Author:wudh
 * @Date: 2019/4/7 20:46
 */
public interface LoginService {

    /**
     * 登录
     * @Author:wudh
     * @Date: 2019/4/7 20:45
     */
    int login(AdminLoginDTO adminLoginDTO);

}
