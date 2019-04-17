package com.chenyifaer.back.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */
@Data
@Accessors(chain = true)
public class AdminMenuDTO {

    public interface Update{};

    public interface Delete{};

    public interface Add{};

    public interface GetOne{};


    /** 主键 */
    @NotNull(groups = {GetOne.class, Update.class , Delete.class} , message = "主键不能为空")
    private Integer adminMenuId;

    /** 菜单名 */
    @Length(max = 30 , message = "菜单名不能超过30个字符")
    @NotNull(groups = {Add.class} , message = "菜单名不能为空")
    private String adminMenuName;

    /** 父菜单ID */
    private Integer adminMenuParentId;

    /** icon */
    @Length(max = 255 , message = "icon路径不能超过255个字符")
    private String icon;

    /** 菜单路由 */
    @Length(max = 255 , message = "菜单路由不能超过255个字符")
    @NotNull(groups = {Add.class} , message = "菜单路由不能为空")
    private String url;

    /** 权重 */
    @Length(max = 999 , message = "权重不能超过999")
    @NotNull(groups = {Add.class} , message = "权重不能为空")
    private Integer weight;

}
