package com.devin.wechatserver.entity.message;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文本消息
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LocationMessage extends BaseMessage {

    /**
     * 地理位置：纬度
     */
    private String Location_X;
    /**
     * 地理位置：经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;

    @Override
    public String getMsgType() {
        return MessageType.LOCATION_MESSAGE.getCode();
    }

}
