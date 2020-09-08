package com.devin.wechatserver.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信工具类
 *
 * @author devin
 */
public class WechatUtil {

    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        List<String> params = new ArrayList<String>();
        params.add(token);
        params.add(timestamp);
        params.add(nonce);
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        params.sort(String::compareTo);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        // 3. 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return temp.equals(signature);
    }

}
