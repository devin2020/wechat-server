package com.devin.wechatserver.entity.output;

import lombok.Data;

/**
 * 图文信息
 *
 * @author devin
 */
@Data
public class Articles {

    /**
     * 标题
     */
    private String Title;
    /**
     * 描述
     */
    private String Description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     */
    private String PicUrl;
    /**
     * 点击图文消息跳转链接
     */
    private String Url;

    public Articles() {
    }

    public Articles(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }

}
