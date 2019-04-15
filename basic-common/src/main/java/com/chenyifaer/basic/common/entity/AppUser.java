package com.chenyifaer.basic.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppUser implements Serializable {

	private static final long serialVersionUID = 611197991672067628L;

	private Integer adminUserId;
	private String adminUserAccount;
	private String adminUserPassword;
	private String adminUserName;
	private Boolean enabled;

}
