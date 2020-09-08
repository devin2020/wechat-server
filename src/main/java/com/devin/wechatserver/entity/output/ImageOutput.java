package com.devin.wechatserver.entity.output;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片回复
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageOutput extends BaseOutput {

    private Image Image;

    @Override
    public String getMsgType() {
        return MessageType.IMAGE_MESSAGE.getCode();
    }

    public ImageOutput(String toUserName, String fromUserName, long createTime, Image image) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.Image = image;
    }
}
