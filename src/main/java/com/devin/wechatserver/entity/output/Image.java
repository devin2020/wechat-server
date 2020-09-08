package com.devin.wechatserver.entity.output;

import lombok.Data;

/**
 * 图片信息
 *
 * @author devin
 */
@Data
public class Image {

    /**
     * 通过上传多媒体文件，得到的id
     */
    private String MediaId;

    public Image(String mediaId) {
        MediaId = mediaId;
    }
}
