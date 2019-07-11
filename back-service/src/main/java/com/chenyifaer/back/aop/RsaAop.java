package com.chenyifaer.back.aop;

import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.config.RSAConfig;
import com.chenyifaer.basic.common.util.rsa.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;


/**
 * 自定义RSA统一加密解密 - AOP
 * @Author:wudh
 * @Date: 2019/4/14 16:20
 */
@Slf4j
@RestControllerAdvice
public class RsaAop implements RequestBodyAdvice {
    
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		try {
			boolean encode = false;
			if(parameter.getMethod().isAnnotationPresent(RsaAnnotation.class)) {
				//获取注解
				RsaAnnotation serializedField = parameter.getMethodAnnotation(RsaAnnotation.class);
				//request参数是否需要解密
				encode = serializedField.inDecode();
			}

			if (!RSAConfig.isRSA) {
			    return inputMessage;
            }
			if(encode) {
				log.debug("对方法method:【"+parameter.getMember().getName()+"】返回数据进行解密");
				return new  MyHttpInputMessage(inputMessage);
			}else {
				log.debug("方法method:【"+parameter.getMember().getName()+"】无需解密");
				return inputMessage;
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("对方法method:【"+parameter.getMember().getName()+"】返回数据进行解密出现异常:{}",e);
			return inputMessage;
		}
	}

	class MyHttpInputMessage implements HttpInputMessage {

		private HttpHeaders headers;

		private InputStream body;

		/**
		 *
		 * @param inputMessage
		 * @throws Exception
		 */
		public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
			this.headers = inputMessage.getHeaders();
			String content = easpString(IOUtils.toString(inputMessage.getBody(),"utf-8"));
			log.debug("需要解密的字符串是"+content);
			String resultContent = content.replace("%", "+");

			try {
				//生成RSA签名
				String sign = RSAUtils.sign(resultContent.getBytes(), RSAConfig.privateKey);
				//校验签名是否正确
				boolean verify = RSAUtils.verify(resultContent.getBytes(), RSAConfig.publicKey, sign);
				if(verify) {
					String decryptDataOnJava = RSAUtils.decryptDataOnJava(resultContent, RSAConfig.privateKey);
					log.debug("解密后的数据是"+decryptDataOnJava);
					this.body = IOUtils.toInputStream(RSAUtils.decryptDataOnJava(resultContent,RSAConfig.privateKey));
				}
			} catch (Exception e) {
				log.error("解密错误");
				e.printStackTrace();
			}

		}

		@Override
		public InputStream getBody() throws IOException {
			return body;
		}

		/**
		 *
		 * @param requestData
		 * @return
		 */
		public String easpString(String requestData){
			if(requestData != null && !requestData.equals("")){
				String s = "{\"data\":";
				if(!requestData.startsWith(s)){
					throw new RuntimeException("参数【requestData】缺失异常！");
				}else{
					int closeLen = requestData.length()-1;
					int openLen = "{\"data\":".length();
					String substring = StringUtils.substring(requestData, openLen, closeLen);
					return substring;
				}
			}
			return "";
		}

		@Override
		public HttpHeaders getHeaders() {
			return headers;
		}
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}



}
