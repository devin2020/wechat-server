package com.devin.wechatserver.entity.output;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 音乐回复
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MusicOutput extends BaseOutput {

    private Music Music;

    @Override
    public String getMsgType() {
        return MessageType.MUSIC_MESSAGE.getCode();
    }

    public MusicOutput(String toUserName, String fromUserName, long createTime, Music music) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.Music = music;
    }
}
