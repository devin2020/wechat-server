package com.devin.wechatserver.entity.output;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文本回复
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TextOutput extends BaseOutput {

    /**
     * 文本消息
     */
    private String Content;

    @Override
    public String getMsgType() {
        return MessageType.TEXT_MESSAGE.getCode();
    }

    public TextOutput() {
    }


    public TextOutput(String toUserName, String fromUserName, long createTime, String content) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.Content = content;
    }

}
