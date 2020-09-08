package com.devin.wechatserver.entity.message;

import lombok.Data;

/**
 * 非事件消息的公共属性
 *
 * @author devin
 */
@Data
public abstract class BaseMessage {

    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     * 消息id，64位整型
     */
    private long MsgId;
    /**
     * 获取 消息类型
     *
     * @return 消息类型
     */
    public abstract String getMsgType();

}
