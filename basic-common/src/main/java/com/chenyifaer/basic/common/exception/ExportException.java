package com.chenyifaer.basic.common.exception;


import com.chenyifaer.basic.common.emuns.ResultCodeEnums;

import java.io.IOException;

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
 * Excel导入导出抛出全局异常
 * @Author:wudh
 * @Date: 2019/4/18 21:45
 */
public class ExportException extends IOException {

    /** 错误类型 */
    protected String failCode;
    /** 错误信息 */
    protected String failMessage;
    /** 返回值 */
    protected ResultCodeEnums failTrace;


    /**
     * 创建错误信息
     */
    public ExportException(ResultCodeEnums resultCodeEnums) {
        super(resultCodeEnums.msg());
        this.failCode = resultCodeEnums.val();
        this.failMessage = resultCodeEnums.msg();
        this.failTrace = resultCodeEnums;
    }

    public String getFailCode() {
        return failCode;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public ResultCodeEnums getFailTrace() {
        return failTrace;
    }
}
