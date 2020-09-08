package com.devin.wechatserver.entity.output;

import lombok.Data;

/**
 * 视频信息
 *
 * @author devin
 */
@Data
public class Video {

    /**
     * 媒体文件id
     */
    private String MediaId;
    /**
     * 缩略图的媒体id
     */
    private String ThumbMediaId;
    /**
     * 标题
     */
    private String Title;
    /**
     * 描述
     */
    private String Description;

    public Video(String mediaId, String thumbMediaId, String title, String description) {
        MediaId = mediaId;
        ThumbMediaId = thumbMediaId;
        Title = title;
        Description = description;
    }
}
