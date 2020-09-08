package com.devin.wechatserver.entity.output;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 语音回复
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VoiceOutput extends BaseOutput {

    private Voice Voice;

    @Override
    public String getMsgType() {
        return MessageType.VOICE_MESSAGE.getCode();
    }

    public VoiceOutput(String toUserName, String fromUserName, long createTime, Voice voice) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.Voice = voice;
    }

}
