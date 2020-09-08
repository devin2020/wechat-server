package com.devin.wechatserver.entity.output;

import com.devin.wechatserver.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 图文回复
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsOutput extends BaseOutput {

    /**
     * 图文消息个数，限制为10条以内,目前微信只支持单图文
     */
    private int ArticleCount = 1;

    private List<Articles> Articles;

    @Override
    public String getMsgType() {
        return MessageType.NEWS_MESSAGE.getCode();
    }

    public NewsOutput() {
    }

    public NewsOutput(String toUserName, String fromUserName, long createTime, int articleCount, List<Articles> articles) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.ArticleCount = articleCount;
        this.Articles = articles;
    }

}
