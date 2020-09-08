package com.devin.wechatserver.entity.output;

import lombok.Data;

/**
 * 音乐信息
 *
 * @author devin
 */
@Data
public class Music {

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
    private String MusicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;
    /**
     * 缩略图的媒体id，通过上传多媒体文件得到的id
     */
    private String ThumbMediaId;

    public Music(String title, String description, String musicUrl, String hQMusicUrl, String thumbMediaId) {
        Title = title;
        Description = description;
        MusicUrl = musicUrl;
        HQMusicUrl = hQMusicUrl;
        ThumbMediaId = thumbMediaId;
    }

}
