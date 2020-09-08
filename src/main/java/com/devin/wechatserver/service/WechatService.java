package com.devin.wechatserver.service;

import java.util.Map;

/**
 * 微信业务
 *
 * @author devin
 */
public interface WechatService {

    public String handleMessage(Map<String, String> map);

}
