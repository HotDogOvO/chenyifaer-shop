package com.chenyifaer.basic.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * feign调用异常配置
 * @Author:wudh
 * @Date: 2019/4/13 17:39
 */
@Slf4j
@RestControllerAdvice
public class FeignClientConfig {

	/**
	 * feignClient调用异常，将服务的异常和http状态码解析
	 */
	@ExceptionHandler({ FeignException.class })
	public Map<String, Object> feignException(FeignException exception, HttpServletResponse response) {
		int httpStatus = exception.status();
		if (httpStatus >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			log.error("feignClient调用异常", exception);
		}

		Map<String, Object> data = new HashMap<>();

		String msg = exception.getMessage();

		if (!StringUtils.isEmpty(msg)) {
			int index = msg.indexOf("\n");
			if (index > 0) {
				String string = msg.substring(index);
				if (!StringUtils.isEmpty(string)) {
					JSONObject json = JSONObject.parseObject(string.trim());
					data.putAll(json.getInnerMap());
				}
			}
		}
		if (data.isEmpty()) {
			data.put("message", msg);
		}

		data.put("code", httpStatus + "");

		response.setStatus(httpStatus);

		return data;
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> badRequestException(IllegalArgumentException exception) {
		Map<String, Object> data = new HashMap<>();
		data.put("code", HttpStatus.BAD_REQUEST.value());
		data.put("message", exception.getMessage());

		return data;
	}

    @ExceptionHandler({Exception.class, Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult serverException(Throwable throwable) {
        log.error("服务端异常", throwable);

        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }

}
