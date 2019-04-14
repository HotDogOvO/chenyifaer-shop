package com.chenyifaer.basic.authorization.config;

import com.chenyifaer.basic.authorization.service.RandomAuthenticationKeyGenerator;
import com.chenyifaer.basic.common.entity.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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
 * Oauth2.0登录认证
 * @Author:wudh
 * @Date: 2019/4/14 15:32
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    /** 登陆后返回的json数据是否追加当前用户信息*/
    @Value("${access_token.add-userinfo}")
    private boolean addUserInfo;

    /**
     * 将client信息存储到oauth_client_details表里
     * @Author:wudh
     * @Date: 2019/4/13 16:00
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * Token存储
     */
    @Bean
    public TokenStore tokenStore() {
        JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource);
        jdbcTokenStore.setAuthenticationKeyGenerator(new RandomAuthenticationKeyGenerator());
        return jdbcTokenStore;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
        //将当前用户信息追加到登陆后返回数据里
        endpoints.tokenEnhancer((accessToken, authentication) -> {
            addLoginUserInfo(accessToken, authentication);
            return accessToken;
        });
    }

    /**
     * 将当前用户信息追加到登陆后返回的json数据里
     * @Author:wudh
     * @Date: 2019/4/13 16:08
     */
    private void addLoginUserInfo(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (!addUserInfo) {
            return;
        }

        if (accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;

            Authentication userAuthentication = authentication.getUserAuthentication();
            Object principal = userAuthentication.getPrincipal();
            if (principal instanceof LoginAppUser) {
                //LoginAppUser loginUser = (LoginAppUser) principal;
                LoginAppUser loginUser = new LoginAppUser();
                Map<String,Object> userDetail = new HashMap<>();
                userDetail.put("adminUserId",((LoginAppUser) principal).getAdminUserId());
                userDetail.put("adminUserAccount",((LoginAppUser) principal).getAdminUserAccount());
                userDetail.put("adminUserName",((LoginAppUser) principal).getAdminUserName());
                userDetail.put("enabled",((LoginAppUser) principal).getEnabled());

                Map<String, Object> map = new HashMap<>(defaultOAuth2AccessToken.getAdditionalInformation()); // 旧的附加参数
                // 追加当前登陆用户
                map.put("userDetail", userDetail);

                defaultOAuth2AccessToken.setAdditionalInformation(map);
            }
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单形式的认证
        security.allowFormAuthenticationForClients();
    }
}
