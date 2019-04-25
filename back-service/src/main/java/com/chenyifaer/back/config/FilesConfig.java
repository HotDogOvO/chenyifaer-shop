package com.chenyifaer.back.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
 * 文件上传配置文件
 * @Author:wudh
 * @Date: 2019/4/21 17:46
 */
@Data
@Component
public class FilesConfig {

    /**
     * 单文件上传最大内存
     */
    @Value("${upload.multipart.maxFileSize}")
    private String maxFileSize;

    /**
     * 多文件上传最大内存
     */
    @Value("${upload.multipart.maxRequestSize}")
    private String maxRequestSize;

    /**
     * 文件上传目录
     */
    @Value("${upload.path.formal}")
    private String formalPath;


}