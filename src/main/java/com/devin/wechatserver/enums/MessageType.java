package com.devin.wechatserver.enums;

/**
 * 消息类型枚举
 *
 * @author devin
 */
public enum MessageType {

    TEXT_MESSAGE("text", "文本消息"),
    /**
     * 目前只支持单图模式
     */
    NEWS_MESSAGE("news", "图文消息"),
    IMAGE_MESSAGE("image", "图片消息"),
    VOICE_MESSAGE("voice", "语音消息"),
    VIDEO_MESSAGE("video", "视频消息"),
    MUSIC_MESSAGE("music", "音乐消息"),
    SHORTVIDEO_MESSAGE("shortvideo", "小视频消息"),
    LOCATION_MESSAGE("location", "地理位置消息"),
    LINK_MESSAGE("link", "链接消息"),
    /**
     * 请求消息类型：事件推送
     */
    MESSAGE_TYPE_EVENT("event", "事件推送"),
    /**
     * 事件类型：CLICK(自定义菜单)
     */
    EVENT_TYPE_CLICK("CLICK", "扫描二维码事件"),
    EVENT_TYPE_SUBSCRIBE("subscribe", "订阅事件"),
    EVENT_TYPE_UNSUBSCRIBE("unsubscribe", "取消订阅事件"),
    /**
     * 事件类型：scan(用户已关注时的扫描带参数二维码)
     */
    EVENT_TYPE_SCAN("SCAN", "扫描二维码事件"),
    ;

    private String code;

    private String name;

    MessageType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
