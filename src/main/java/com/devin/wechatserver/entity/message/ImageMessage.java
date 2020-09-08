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
public class ImageMessage extends BaseMessage {

    /**
     * 图片链接
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String MediaId;

    @Override
    public String getMsgType() {
        return MessageType.IMAGE_MESSAGE.getCode();
    }

}
