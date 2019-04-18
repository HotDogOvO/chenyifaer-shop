package com.chenyifaer.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.back.entity.dto.WebUserDetailDTO;
import com.chenyifaer.back.entity.po.WebUserDetailPO;
import com.chenyifaer.back.entity.vo.WebUserDetailVO;

import java.util.List;

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
 * 前台账号管理 - 前台用户详情表 Mapper 接口
 * @author wudh
 * @since 2019-04-18
 */
public interface WebUserDetailDao extends BaseMapper<WebUserDetailPO> {

    /**
     * 根據用戶ID查詢用戶詳情
     * @Author:wudh
     * @Date: 2019/4/18 15:47
     */
    List<WebUserDetailVO> getUserDetail(WebUserDetailDTO webUserDetailDTO);

}
