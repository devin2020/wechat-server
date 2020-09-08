package com.devin.wechatserver.entity.output;

import lombok.Data;

/**
 * 语音信息
 *
 * @author devin
 */
@Data
public class Voice {

    /**
     * 通过上传多媒体文件，得到的id
     */
    private String MediaId;

    public Voice(String mediaId) {
        MediaId = mediaId;
    }
}
