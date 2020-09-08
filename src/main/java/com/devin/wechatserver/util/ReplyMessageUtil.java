package com.devin.wechatserver.util;

import com.devin.wechatserver.entity.output.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 构建回复消息
 *
 * @author devin
 */
public class ReplyMessageUtil implements Serializable {

    /**
     * 构建公共输出部分
     */
    private static StringBuffer buildBaseOutput(BaseOutput output) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(output.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(output.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(output.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[").append(output.getMsgType()).append("]]></MsgType>");
        return sb;
    }

    /**
     * 构建图文回复消息
     */
    public static String buildNewsOutput(NewsOutput output) {
        StringBuffer sb = buildBaseOutput(output);
        sb.append("<ArticleCount>").append(output.getArticleCount()).append("</ArticleCount>");
        sb.append("<Articles> ");
        for (Articles article : output.getArticles()) {
            sb.append("<item>");
            if (StringUtils.hasText(article.getTitle())) {
                sb.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>");
            }
            if (StringUtils.hasText(article.getDescription())) {
                sb.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>");
            }
            if (StringUtils.hasText(article.getPicUrl())) {
                sb.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>");
            }
            if (StringUtils.hasText(article.getUrl())) {
                sb.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>");
            }
            sb.append("</item>");
        }
        sb.append("</Articles>");
        sb.append("<FuncFlag>0</FuncFlag>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 构建文本回复消息
     */
    public static String buildTextOutput(TextOutput output) {
        StringBuffer sb = buildBaseOutput(output);
        sb.append("<Content><![CDATA[").append(output.getContent()).append("]]></Content>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 构建图片回复消息
     */
    public static String buildImageOutput(ImageOutput output) {
        StringBuffer sb = buildBaseOutput(output);
        sb.append("<Image>");
        sb.append("<MediaId><![CDATA[").append(output.getImage().getMediaId()).append("]]></MediaId>");
        sb.append("</Image>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 构建语音回复消息
     */
    public static String buildVoiceOutput(VoiceOutput output) {
        StringBuffer sb = buildBaseOutput(output);
        sb.append("<Voice>");
        sb.append("<MediaId><![CDATA[").append(output.getVoice().getMediaId()).append("]]></MediaId>");
        sb.append("</Voice>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 构建视频回复消息
     */
    public static String buildVideoOutput(VideoOutput output) {
        StringBuffer sb = buildBaseOutput(output);
        sb.append("<Video>");
        sb.append("<MediaId><![CDATA[").append(output.getVideo().getMediaId()).append("]]></MediaId>");
        if (StringUtils.hasText(output.getVideo().getTitle())) {
            sb.append("<Title><![CDATA[").append(output.getVideo().getTitle()).append("]]></Title>");
        }
        if (StringUtils.hasText(output.getVideo().getDescription())) {
            sb.append("<Description><![CDATA[").append(output.getVideo().getDescription()).append("]]></Description>");
        }
        sb.append("</Video>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 构建音乐回复消息
     */
    public static String buildMusicOutput(MusicOutput output) {
        StringBuffer sb = buildBaseOutput(output);
        sb.append("<Music>");
        if (StringUtils.hasText(output.getMusic().getTitle())) {
            sb.append("<Title><![CDATA[").append(output.getMusic().getTitle()).append("]]></Title>");
        }
        if (StringUtils.hasText(output.getMusic().getDescription())) {
            sb.append("<Description><![CDATA[").append(output.getMusic().getDescription()).append("]]></Description>");
        }
        if (StringUtils.hasText(output.getMusic().getMusicUrl())) {
            sb.append("<MusicUrl><![CDATA[").append(output.getMusic().getMusicUrl()).append("]]></MusicUrl>");
        }
        if (StringUtils.hasText(output.getMusic().getHQMusicUrl())) {
            sb.append("<HQMusicUrl><![CDATA[").append(output.getMusic().getHQMusicUrl()).append("]]></HQMusicUrl>");
        }
        if (StringUtils.hasText(output.getMusic().getThumbMediaId())) {
            sb.append("<ThumbMediaId><![CDATA[").append(output.getMusic().getThumbMediaId()).append("]]></ThumbMediaId>");
        }
        sb.append("</Music>");
        sb.append("</xml>");
        return sb.toString();
    }




}
