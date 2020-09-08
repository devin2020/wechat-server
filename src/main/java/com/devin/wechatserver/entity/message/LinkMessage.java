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
public class LinkMessage extends BaseMessage {

    /**
     * 标题
     */
    private String Title;
    /**
     * 描述
     */
    private String Description;
    /**
     * 链接
     */
    private String Url;

    @Override
    public String getMsgType() {
        return MessageType.LINK_MESSAGE.getCode();
    }

}
