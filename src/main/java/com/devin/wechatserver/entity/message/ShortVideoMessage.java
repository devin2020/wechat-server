package com.devin.wechatserver.entity.message;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShortVideoMessage extends BaseMessage {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String MediaId;
    /**
     * 视频消息 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String ThumbMediaId;

    @Override
    public String getMsgType() {
        return MessageType.SHORTVIDEO_MESSAGE.getCode();
    }

}
