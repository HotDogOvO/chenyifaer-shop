package com.chenyifaer.basic.common.util;

import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
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
 * Springboot - Validated数据校验
 * @Author:wudh
 * @Date: 2019/4/14 16:14
 */
public class CheckUtil {

    public static JsonResult check(BindingResult br){
        if(br.getErrorCount()>0){
            HashMap<Object, Object> vData = new HashMap<>();
            br.hasErrors();
            List<ObjectError> allErrors = br.getAllErrors();
            for (ObjectError allError : allErrors) {
                FieldError fError = (FieldError) allError;
                String field = fError.getField();
                String defaultMessage = fError.getDefaultMessage();
                vData.put(field,defaultMessage);
                return  ResponseResult.Fail(ResultCodeEnums.CHECK_001, fError.getDefaultMessage());
            }
        }

        return null;
    }

}
