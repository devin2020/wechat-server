package com.devin.wechatserver.entity.output;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频回复
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoOutput extends BaseOutput {

    private Video Video;

    @Override
    public String getMsgType() {
        return MessageType.VIDEO_MESSAGE.getCode();
    }

    public VideoOutput(String toUserName, String fromUserName, long createTime, Video video) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.Video = video;
    }
}
