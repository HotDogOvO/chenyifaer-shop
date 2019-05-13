package com.chenyifaer.web.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.UserAddressDTO;
import com.chenyifaer.web.entity.po.WebUserAddressPO;
import com.chenyifaer.web.entity.vo.UserAddressVO;
import com.chenyifaer.web.service.WebUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台账号管理 - 前台用户地址表 前端控制器
 * @author wudh
 * @since 2019-05-09
 */

@Slf4j
@RestController
@RequestMapping("/user/address")
@Api(value = "用户管理",tags = {"用户管理 - 用户地址管理"})
public class WebUserAddressController {

    @Autowired
    private WebUserAddressService webUserAddressService;

    @ApiOperation(value = "订单详情页 - 获取用户收货地址")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getAddress" , method = RequestMethod.POST)
    public JsonResult getAddress(@RequestBody @Validated(UserAddressDTO.Select.class) UserAddressDTO userAddressDTO, BindingResult br) {
        log.debug("【START】 - function WebUserAddressController - getAddress");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserAddressController - getAddress 参数校验失败");
            return check;
        }

        List<WebUserAddressPO> list = this.webUserAddressService.list(new QueryWrapper<>(new WebUserAddressPO().setUserId(userAddressDTO.getUserId())));
        List<UserAddressVO> addressList = new ArrayList<>();

        list.forEach(x -> {
            addressList.add(new UserAddressVO()
                    .setAddressId(x.getAddressId())
                    .setConsigneeName(x.getConsigneeName())
                    .setConsigneePhone(x.getConsigneePhone())
                    .setProvince(x.getProvince())
                    .setCity(x.getCity())
                    .setArea(x.getArea())
                    .setAddress(x.getAddress())
                    .setDefaultStatus(x.getDefaultStatus()));
        });
        log.debug("【END】 - function end WebUserAddressController - getAddress，查询用户地址成功，查询的结果为：" + addressList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS, addressList);
    }

    @ApiOperation(value = "订单详情页 - 新增收货地址")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "string"),
        @ApiImplicitParam(name = "consigneeName", value = "收货人姓名", dataType = "string"),
        @ApiImplicitParam(name = "consigneePhone", value = "收货人手机号", dataType = "string"),
        @ApiImplicitParam(name = "province", value = "省", dataType = "string"),
        @ApiImplicitParam(name = "city", value = "市", dataType = "string"),
        @ApiImplicitParam(name = "area", value = "区", dataType = "string"),
        @ApiImplicitParam(name = "address", value = "详细地址", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(UserAddressDTO.Add.class) UserAddressDTO userAddressDTO, BindingResult br) {
        log.debug("【START】 - function WebUserAddressController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserAddressController - add 参数校验失败");
            return check;
        }
        boolean flag = this.webUserAddressService.save(new WebUserAddressPO()
                .setUserId(userAddressDTO.getUserId())
                .setConsigneeName(userAddressDTO.getConsigneeName())
                .setConsigneePhone(userAddressDTO.getConsigneePhone())
                .setProvince(userAddressDTO.getProvince())
                .setCity(userAddressDTO.getCity())
                .setArea(userAddressDTO.getArea())
                .setAddress(userAddressDTO.getAddress()));
        if(flag){
            log.debug("【END】 - function end WebUserAddressController - add，新增地址成功");
            return ResponseResult.Success(ResultCodeEnums.SUCCESS);
        }
        log.error("【ERROR】 - function error WebUserAddressController - add，新增地址失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }
}
