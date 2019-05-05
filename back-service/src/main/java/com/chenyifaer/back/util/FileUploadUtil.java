package com.chenyifaer.back.util;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.chenyifaer.back.config.FilesConfig;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传 - 工具类
 * @Author:wudh
 * @Date: 2019/4/21 17:43
 */
@Slf4j
public class FileUploadUtil {

    @Autowired
    private FilesConfig filesConfig;

    /**
     * 设置文件上传大小：在配置文件中限定
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        log.debug("【START】 - function multipartConfigElement");
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize(filesConfig.getMaxFileSize());
        // 设置总上传数据总大小
        factory.setMaxRequestSize(filesConfig.getMaxRequestSize());
        log.debug("【END】 - function multipartConfigElement");
        return factory.createMultipartConfig();
    }

    /**
     * 文件上传
     *      file - 文件
     *      formalPath - 文件存储路径 - config取得
     *      mkdirUrl - 各模块图片存储文件夹名
     * @Author:wudh
     * @Date: 2019/4/24 12:01
     */
    public static JsonResult upload(MultipartFile file,String formalPath,String mkdirUrl){
        JsonResult jsonResult ;
        InputStream in = null;
        FileOutputStream fs = null;

        try {
            // 创建目录
            String filePath = FileUploadUtil.createPath(formalPath + mkdirUrl);
            // 新的文件存放路径加上新的文件名
            String newFileName = filePath + FileUploadUtil.getFileName(file.getOriginalFilename());
            File newFile = new File(newFileName);

            // 存储文件
            log.debug("【RUN】 - function upload - 开始保存文件 - {} ", newFileName);
            in = file.getInputStream();
            fs = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024 * 1024];
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
                fs.flush();
            }

            log.debug("【RUN】 - function upload - 保存文件 - {} - 完成 ", newFileName);

            // 去掉目录名，保留文件总体路径，通过该路径访问图片
            String filename = newFileName.substring(newFileName.lastIndexOf(formalPath)).replace(formalPath, "");
            jsonResult = ResponseResult.Success(ResultCodeEnums.SUCCESS_006, filename);
        } catch (IOException e) {
            log.error("【ERROR】 - 文件上传失败 :" , e);
            e.printStackTrace();
            jsonResult = ResponseResult.Fail(ResultCodeEnums.FAIL_10006);
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException closeEx) {
                    log.error("关闭输出流失败 ", closeEx);
                    closeEx.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException closeEx) {
                    log.error("关闭输入流失败 ", closeEx);
                    closeEx.printStackTrace();
                }
            }

        }
        return jsonResult;
    }



    /**
     * 文件重新命名
     * @Author:wudh
     * @Date: 2019/4/21 18:05
     */
    public static String getFileName(String fileOriginName) {
        StringBuffer fileName = new StringBuffer(UUID.randomUUID().toString().replace("-", ""));
        fileName.append(DateUtil.getHHmmssDate(new Date()));
        fileName.append(fileOriginName.substring(fileOriginName.lastIndexOf(".")));

        return fileName.toString();
    }

    /**
     * 创建文件夹
     * @Author:wudh
     * @Date: 2019/4/21 18:05
     */
    public static String createPath(String path) {
        String uploudTime = DateUtil.getYYYYMMDDBySprit(new Date());

        // 新的文件存放路径加上新的文件名
        String newPath = path + uploudTime + "/";
        File file = new File(newPath);
        log.debug("【RUN】 - 文件上传 - 创建文件夹 path : " + file.getAbsolutePath());
        // 判断文件目录是否存在
        if (!file.exists()) {
            // 创建文件存放目录
            file.mkdirs();
        }
        return newPath;
    }

}
