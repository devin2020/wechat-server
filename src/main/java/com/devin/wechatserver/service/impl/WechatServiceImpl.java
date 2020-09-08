package com.devin.wechatserver.service.impl;

import com.devin.wechatserver.entity.output.*;
import com.devin.wechatserver.enums.MessageType;
import com.devin.wechatserver.service.WechatService;
import com.devin.wechatserver.util.ReplyMessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Description
 *
 * @author devin
 */
@Service
public class WechatServiceImpl implements WechatService {

    @Value("${wechat.welcome-title}")
    private String welcomeTitle;
    @Value("${wechat.welcome-description}")
    private String welcomeDescription;
    @Value("${wechat.welcome-pic-url}")
    private String welcomePicUrl;
    @Value("${wechat.welcome-url}")
    private String welcomeUrl;

    @Override
    public String handleMessage(Map<String, String> map) {
        String MsgType = map.get("MsgType");
        if (MessageType.MESSAGE_TYPE_EVENT.getCode().equals(MsgType)) {
            return parseEvent(map);
        } else {
            return parseMessage(map);
        }
    }

    /**
     * 处理事件：
     * <p>订阅事件，回复欢迎图文
     * <p>自定义菜单点击事件，回复文案
     */
    private String parseEvent(Map<String, String> map) {
        // 获取事件类型
        String Event = map.get("Event");
        String toUserName = map.get("FromUserName");
        String fromUserName = map.get("ToUserName");
        long createTime = new Date().getTime();
        MessageType.EVENT_TYPE_SUBSCRIBE.getCode();
        // 订阅：推送欢迎图文
        if (MessageType.EVENT_TYPE_SUBSCRIBE.getCode().equals(Event)) {
            return handleWelcomeMessage(toUserName, fromUserName, createTime);
        }
        if (MessageType.EVENT_TYPE_CLICK.getCode().equals(Event)) {
            // 点击的key，对应自定义菜单的key
            String EventKey = map.get("EventKey");
            String content = "现在正在点击按钮：" + EventKey;
            TextOutput output = new TextOutput(toUserName, fromUserName, createTime, content);
            return ReplyMessageUtil.buildTextOutput(output);
        }
        return null;
    }

    private String handleWelcomeMessage(String toUserName, String fromUserName, long createTime) {
        Articles articles = new Articles(welcomeTitle, welcomeDescription, welcomePicUrl, welcomeUrl);
        List<Articles> list = new ArrayList<>();
        list.add(articles);
        NewsOutput output = new NewsOutput(toUserName, fromUserName, createTime, 1, list);
        return ReplyMessageUtil.buildNewsOutput(output);
    }

    /**
     * 处理消息：
     * <p>图片消息，回复斗图
     * <p>语音消息，回复长语音
     * <p>位置消息，回复当前经纬度等
     * <p>文本消息：
     * <p>1、“help”/“HELP”，回复欢迎图文
     * <p>2、包含“听歌”，回复随机歌曲（"听歌 歌曲/歌手"，回复指定歌曲或歌手第一首歌）
     * <p>3、包含“用友”/“司歌”，回复司歌视频
     * <p>4、其他消息，个性处理
     */
    private String parseMessage(Map<String, String> map) {
        String MsgType = map.get("MsgType");
        String toUserName = map.get("FromUserName");
        String fromUserName = map.get("ToUserName");
        long createTime = new Date().getTime();
        if (MessageType.IMAGE_MESSAGE.getCode().equals(MsgType)) {
            return handleImageMessage(toUserName, fromUserName, createTime);
        }
        if (MessageType.VOICE_MESSAGE.getCode().equals(MsgType)) {
            return handleVoiceMessage(toUserName, fromUserName, createTime);
        }
        String content = "不能理解您的操作，请尝试其他服务";
        if (MessageType.LOCATION_MESSAGE.getCode().equals(MsgType)) {
            String y = map.get("Location_Y");
            String x = map.get("Location_X");
            String addr = map.get("Label");
            content = "你的地理位置：" + addr + "，经纬度：" + y + "|" + x;
        }
        if (MessageType.TEXT_MESSAGE.getCode().equals(MsgType)) {
            String keyword = map.get("Content").trim();
            // 帮助，回复欢迎图文
            if (keyword.equalsIgnoreCase("help")) {
                return handleWelcomeMessage(toUserName, fromUserName, createTime);
            }
            if (keyword.contains("听歌")) {
                return handleMusicMessage(toUserName, fromUserName, createTime, keyword);
            }
            if (keyword.contains("用友") || keyword.contains("司歌")) {
                return handleCompanyMessage(toUserName, fromUserName, createTime);
            }
        }
        TextOutput output = new TextOutput(toUserName, fromUserName, createTime, content);
        return ReplyMessageUtil.buildTextOutput(output);
    }

    private String handleCompanyMessage(String toUserName, String fromUserName, long createTime) {
        String[] company = {"lW9ZEHBKMgjrFjgGqXcHHEiBF-8U2MisGBc6xm1MMvdUqngHT5AC3uoyPIrN9rwx", "司歌", "我们都是“真心英雄”"};
        Video video = new Video(company[0], null, company[1], company[2]);
        VideoOutput output = new VideoOutput(toUserName, fromUserName, createTime, video);
        return ReplyMessageUtil.buildVideoOutput(output);
    }

    private String handleMusicMessage(String toUserName, String fromUserName, long createTime, String keyword) {
        String[][] musics = {
                {"一人我饮酒醉", "冯提莫", "http://182.92.230.69/php/music/yrwyjz.mp3"},
                {"我是一颗跳跳糖", "大张伟", "http://182.92.230.69/php/music/wsykttt.mp3"},
                {"我怎么这么好看", "大张伟", "http://182.92.230.69/php/music/wzmzmhk.mp3"},
                {"奔跑", "羽泉", "http://182.92.230.69/php/music/bp.mp3"},
                {"鸭梨大", "至上励合", "http://182.92.230.69/php/music/yld.mp3"},
                {"改变自己", "王力宏", "http://182.92.230.69/php/music/gbzj.mp3"},
                {"突然的自我", "伍佰", "http://182.92.230.69/php/music/trdzw.mp3"},
                {"大田后生仔", "丫蛋蛋", "http://182.92.230.69/php/music/dthsz.mp3"},
                {"恭喜发财", "刘德华", "http://182.92.230.69/php/music/gxfc.mp3"},
                {"小星星", "汪苏泷", "http://182.92.230.69/php/music/xxx.mp3"},
                {"归去来兮", "花粥", "http://182.92.230.69/php/music/gqlx.mp3"},
                {"小苹果", "筷子兄弟", "http://182.92.230.69/php/music/xpg.mp3"},
                {"中国话", "S.H.E", "http://182.92.230.69/php/music/zgh.mp3"},
                {"大梦想家", "TFBOYS", "http://182.92.230.69/php/music/dmxj.mp3"},
                {"青春修炼手册", "TFBOYS", "http://182.92.230.69/php/music/qcxlsc.mp3"},
                {"最炫民族风", "凤凰传奇", "http://182.92.230.69/php/music/zxmzf.mp3"},
                {"第一次", "光良", "http://182.92.230.69/php/music/dyc.mp3"},
                {"九儿", "韩红", "http://182.92.230.69/php/music/je.mp3"},
                {"那片海", "韩红", "http://182.92.230.69/php/music/nph.mp3"},
                {"蜉蝣", "华晨宇", "http://182.92.230.69/php/music/fy.mp3"},
                {"high歌", "黄玲", "http://182.92.230.69/php/music/highg.mp3"},
                {"卡路里", "火箭少女101", "http://182.92.230.69/php/music/kll.mp3"},
                {"大王叫我来巡山", "贾乃亮", "http://182.92.230.69/php/music/dwjwlxs.mp3"},
                {"同桌的你", "老狼", "http://182.92.230.69/php/music/tzdn.mp3"},
                {"风吹麦浪", "李健", "http://182.92.230.69/php/music/fcml.mp3"},
                {"DI DA DI", "李玟", "http://182.92.230.69/php/music/ddd.mp3"},
                {"新贵妃醉酒", "李玉刚", "http://182.92.230.69/php/music/xgfzj.mp3"},
                {"你是我的眼", "林宥嘉", "http://182.92.230.69/php/music/nswdy.mp3"},
                {"Opera", "林志炫", "http://182.92.230.69/php/music/opera.mp3"},
                {"没离开过", "林志炫", "http://182.92.230.69/php/music/mlkg.mp3"},
                {"蒙娜丽莎的眼泪", "林志炫", "http://182.92.230.69/php/music/mnlsdyl.mp3"},
                {"你到底爱着谁", "刘嘉亮", "http://182.92.230.69/php/music/nddas.mp3"},
                {"唱假声的男人", "罗中旭", "http://182.92.230.69/php/music/cjsdnr.mp3"},
                {"不染", "毛不易", "http://182.92.230.69/php/music/br.mp3"},
                {"不得不爱", "潘玮柏", "http://182.92.230.69/php/music/bdba.mp3"},
                {"齐天", "华晨宇", "http://182.92.230.69/php/music/qt.mp3"},
                {"暗香", "沙宝亮", "http://182.92.230.69/php/music/ax.mp3"},
                {"美丽的神话", "孙楠", "http://182.92.230.69/php/music/mldsh.mp3"},
                {"不灭的心", "孙楠", "http://182.92.230.69/php/music/bmdx.mp3"},
                {"如果有来生", "谭维维", "http://182.92.230.69/php/music/rgyls.mp3"},
                {"小情歌", "吴青峰", "http://182.92.230.69/php/music/xqg.mp3"},
                {"老鼠爱大米", "香香", "http://182.92.230.69/php/music/lsadm.mp3"},
                {"你懂得", "小沈阳", "http://182.92.230.69/php/music/ndd.mp3"},
                {"大笑江湖", "小沈阳", "http://182.92.230.69/php/music/dxjh.mp3"},
                {"花香", "许绍洋", "http://182.92.230.69/php/music/hx.mp3"},
                {"那一天", "杨坤", "http://182.92.230.69/php/music/nyt.mp3"},
                {"芒种", "音阙诗听", "http://182.92.230.69/php/music/mz.mp3"},
                {"在一起", "羽泉", "http://182.92.230.69/php/music/zyq.mp3"},
                {"大中国", "羽泉", "http://182.92.230.69/php/music/dzg.mp3"},
                {"当你孤单你会想起谁", "张栋梁", "http://182.92.230.69/php/music/dngdnhxqs.mp3"},
                {"酸酸甜甜就是我", "张含韵", "http://182.92.230.69/php/music/ssttjsw.mp3"},
                {"隐形的翅膀", "张韶涵", "http://182.92.230.69/php/music/yxdcb.mp3"},
                {"过火", "张信哲", "http://182.92.230.69/php/music/gh.mp3"},
                {"爱的初体验", "张震岳", "http://182.92.230.69/php/music/adcty.mp3"},
                {"爱我别走", "张震岳", "http://182.92.230.69/php/music/awbz.mp3"},
                {"水手", "郑智化", "http://182.92.230.69/php/music/ss.mp3"},
                {"棉花糖", "至上励合", "http://182.92.230.69/php/music/mht.mp3"},
                {"笔记", "周笔畅", "http://182.92.230.69/php/music/bj.mp3"},
                {"听妈妈的话", "周杰伦", "http://182.92.230.69/php/music/tmmdh.mp3"},
                {"菊花台", "周杰伦", "http://182.92.230.69/php/music/jht.mp3"},
                {"迷迭香", "周杰伦", "http://182.92.230.69/php/music/mdx.mp3"},
                {"青花瓷", "周杰伦", "http://182.92.230.69/php/music/qhc.mp3"},
                {"画", "邓紫棋", "http://182.92.230.69/php/music/hua.mp3"}
        };
        int musicIdx = (int) (Math.random() * musics.length);
        if (keyword.contains("听歌 ")) {
            String word = keyword.split(" ")[1];
            if (StringUtils.hasText(word)) {
                for (int i = 0; i < musics.length; i++) {
                    if (word.equals(musics[i][0]) || word.equals(musics[i][1])) {
                        musicIdx = i;
                        break;
                    }
                }
            }
        }

        String[] musicInfo = musics[musicIdx];
        Music music = new Music(musicInfo[0], musicInfo[1], musicInfo[2], musicInfo[2], null);
        MusicOutput output = new MusicOutput(toUserName, fromUserName, createTime, music);
        return ReplyMessageUtil.buildMusicOutput(output);
    }

    private String handleVoiceMessage(String toUserName, String fromUserName, long createTime) {
        // 长语音id
        String MediaId = "APX-0cMJ_Lbv3u7vHYB_XEmZx7TXo-7UN3THx-93X05H7-wm63HNFKPo0vCBlWOy";
        Voice voice = new Voice(MediaId);
        VoiceOutput output = new VoiceOutput(toUserName, fromUserName, createTime, voice);
        return ReplyMessageUtil.buildVoiceOutput(output);
    }

    private String handleImageMessage(String toUserName, String fromUserName, long createTime) {
        String[] images = {"T1x6LpML9ahiD7ED3cVOWpnrCcTt-SU6yzM_IiYBC4U", "T1x6LpML9ahiD7ED3cVOWrQ7BwZmtuIGtc1Xtst_UBw",
                "T1x6LpML9ahiD7ED3cVOWmnsGckH52EXH7m4ayOaI1w", "T1x6LpML9ahiD7ED3cVOWsv01BsbeowTzYlJ_8yMtkQ",
                "T1x6LpML9ahiD7ED3cVOWuLbqa22fr29TF-KmBGGJQs", "T1x6LpML9ahiD7ED3cVOWk_wpkYiBGJHyngyZY4eoCo",
                "T1x6LpML9ahiD7ED3cVOWideP_QGUd72YA9usuBuyjo", "T1x6LpML9ahiD7ED3cVOWlC5Qbj9nNwAjlyFG8xKs1Y",
                "T1x6LpML9ahiD7ED3cVOWoVcY_B_M1hA7unJLtwlBw8"};

        Image image = new Image(images[(int) (Math.random() * images.length)]);
        ImageOutput output = new ImageOutput(toUserName, fromUserName, createTime, image);
        return ReplyMessageUtil.buildImageOutput(output);
    }

}
