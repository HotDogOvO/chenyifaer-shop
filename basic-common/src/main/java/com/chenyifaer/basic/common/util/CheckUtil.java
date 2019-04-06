package com.chenyifaer.basic.common.util;

import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emun.ResultCodeEnums;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;

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
                return  ResponseResult.Fail(ResultCodeEnums.CHECK, fError.getDefaultMessage());
            }
        }

        return null;
    }

}
