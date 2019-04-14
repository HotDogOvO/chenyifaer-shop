package com.chenyifaer.basic.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Oauth2.0服务验证
 * @Author:wudh
 * @Date: 2019/4/13 17:04
 */
@FeignClient(value = "oauth-service" , url = "127.0.0.1:7004")
public interface Oauth2Client {

	/**
	 * 获取access_token
	 */
	@PostMapping(path = "/oauth/token")
	Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);

	/**
	 * 删除access_token和refresh_token
	 */
	@DeleteMapping(path = "/remove_token")
	void removeToken(@RequestParam("access_token") String access_token);

}
