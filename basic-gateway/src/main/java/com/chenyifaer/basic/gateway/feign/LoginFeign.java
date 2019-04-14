package com.chenyifaer.basic.gateway.feign;

import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.gateway.entity.dto.AdminLoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

/**
 * 后台系统登录 - feign声明式调用
 * @Author:wudh
 * @Date: 2019/4/7 20:58
 */
@FeignClient(value = "back-service" )
public interface LoginFeign {

    /**
     * 登录
     * @Author:wudh
     * @Date: 2019/4/7 20:58
     */
    @RequestMapping(value = "/sys/login/login" , method = RequestMethod.POST)
    JsonResult login(@RequestBody AdminLoginDTO adminLoginDTO);

}
