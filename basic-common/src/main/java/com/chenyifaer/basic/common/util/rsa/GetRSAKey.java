package com.chenyifaer.basic.common.util.rsa;

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
 * RSA加密生成签名
 * 运行后会在D盘下生成两个文件：publicKey,privateKey
 * 分别对应私钥与公钥
 * @Author:wudh
 * @Date: 2019/1/20 13:41
 */
public class GetRSAKey {

    public static void main(String[] args) throws Exception {
        String filepath="D:/";
        RSAEncrypt.genKeyPair(filepath);
    }
}
