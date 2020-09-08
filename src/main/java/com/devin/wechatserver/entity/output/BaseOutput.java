package com.devin.wechatserver.entity.output;

import lombok.Data;

/**
 * 回复消息的公共属性
 *
 * @author devin
 */
@Data
public abstract class BaseOutput {

    /**
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     * 获取消息类型
     */
    public abstract String getMsgType();

}
