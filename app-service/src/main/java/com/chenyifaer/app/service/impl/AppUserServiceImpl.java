package com.chenyifaer.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.app.config.WechatConfig;
import com.chenyifaer.app.constant.WechatConstant;
import com.chenyifaer.app.dao.AppUserDao;
import com.chenyifaer.app.entity.dto.LoginDTO;
import com.chenyifaer.app.entity.dto.WechatLoginDTO;
import com.chenyifaer.app.entity.po.AppUserPO;
import com.chenyifaer.app.service.AppUserService;
import com.chenyifaer.basic.common.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 小程序端 - 用户表 服务实现类
 * </p>
 *
 * @author wudh
 * @since 2019-07-06
 */
@Slf4j
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserDao, AppUserPO> implements AppUserService {

    @Autowired
    private AppUserDao appUserDao;

    @Override
    public String login(LoginDTO loginDTO) {
        try{
            //通过code获取openId及session_key
            JSONObject jsonObject = OkHttpUtil.doGetJson(WechatConstant.LOGIN_URL
                    .replace("${appId}", WechatConfig.appId)
                    .replace("${appSecret}", WechatConfig.appSecret)
                    .replace("${grantType}", WechatConfig.grantType)
                    .replace("${jsCode}", loginDTO.getCode()));
            log.debug("【RUN】 - function AppUserServiceImpl - login - 获取的数据为【{}】",jsonObject);

            WechatLoginDTO wechatLoginDTO = jsonObject.toJavaObject(WechatLoginDTO.class);

            //判断返回的openid是否为空
            if(!StringUtils.isEmpty(wechatLoginDTO.getOpenId())){
                //取出openId
                String openId = jsonObject.get("openid").toString();
                //判断当前用户是否存在
                int count = this.appUserDao.selectCount(new QueryWrapper<>(new AppUserPO().setOpenId(openId)));
                //数量大于0 代表已经存在
                if(count > 0){
                    log.debug("【RUN】 - function AppUserServiceImpl - login - 当前用户已经存在了，用户的OpenId为【{}】",openId);
                    return openId;
                }
                //插入数据库
                int num = this.appUserDao.insert(new AppUserPO()
                        .setOpenId(openId)
                        .setAvatarUrl(loginDTO.getAvatarUrl())
                        .setNickName(loginDTO.getNickName())
                        .setGender(loginDTO.getGender())
                        .setCountry(loginDTO.getCountry())
                        .setProvince(loginDTO.getProvince())
                        .setCity(loginDTO.getCity())
                        .setLanguage(loginDTO.getLanguage()));
                //插入成功
                if(num > 0){
                    log.debug("【RUN】 - function AppUserServiceImpl - login - 当前用户为新用户，插入成功，插入的OpenId为【{}】",openId);
                    return openId;
                }
            }
        } catch(Exception e){
            log.error("【ERROR】 - function AppUserServiceImpl - login - 登录失败，原因是【{}】",e);
            e.printStackTrace();
        }
        return null;
    }
}
