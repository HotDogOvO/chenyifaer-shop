package com.chenyifaer.web.entity.dto;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 用户地址 - DTO
 * @Author:wudh
 * @Date: 2019/5/13 13:59
 */

@Data
@Accessors(chain = true)
public class UserAddressDTO {

    public interface Select{};

    public interface Add{};

    /** 用户ID */
    @NotNull(groups = {UserAddressDTO.Select.class,UserAddressDTO.Add.class} , message = "参数不能为空")
    private Integer userId;

    /** 收货人姓名 */
    @NotNull(groups = {UserAddressDTO.Add.class} , message = "参数不能为空")
    private String consigneeName;

    /** 收货人手机号 */
    @NotNull(groups = {UserAddressDTO.Add.class} , message = "参数不能为空")
    private String consigneePhone;

    /** 省 */
    @NotNull(groups = {UserAddressDTO.Add.class} , message = "参数不能为空")
    private String province;

    /** 市 */
    @NotNull(groups = {UserAddressDTO.Add.class} , message = "参数不能为空")
    private String city;

    /** 区 */
    @NotNull(groups = {UserAddressDTO.Add.class} , message = "参数不能为空")
    private String area;

    /** 详细地址 */
    @NotNull(groups = {UserAddressDTO.Add.class} , message = "参数不能为空")
    private String address;
}
