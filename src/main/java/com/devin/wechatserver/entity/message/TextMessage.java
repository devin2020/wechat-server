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
public class TextMessage extends BaseMessage {

    /**
     * 文本消息内容
     */
    private String Content;

    @Override
    public String getMsgType() {
        return MessageType.TEXT_MESSAGE.getCode();
    }

}
