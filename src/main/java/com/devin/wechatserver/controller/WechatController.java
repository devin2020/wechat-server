package com.devin.wechatserver.controller;

import com.devin.wechatserver.service.WechatService;
import com.devin.wechatserver.util.WechatUtil;
import com.devin.wechatserver.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信公众号消息处理
 *
 * @author devin
 */
@Slf4j
@RestController
@RequestMapping("/weixin")
@CrossOrigin
public class WechatController {

    @Value("${wechat.token}")
    private String token;

    @Autowired
    private WechatService wechatService;

    @GetMapping
    public String checkSignature(HttpServletRequest request) {
        try {
            //消息来源可靠性验证
            String signature = request.getParameter("signature");   // 微信加密签名
            String timestamp = request.getParameter("timestamp");   // 时间戳
            String nonce = request.getParameter("nonce");           // 随机数
            String echostr = request.getParameter("echostr");       //成为开发者验证
            //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功；否则接入失败
            log.info(String.format("checkSignature -> signature=%s&timestamp=%s&nonce=%s&echostr=%s",
                    signature, timestamp, nonce, echostr));
            if (WechatUtil.checkSignature(token, signature, timestamp, nonce)) {
                log.info("checkSignature success...");
                return echostr;
            } else {
                log.info("checkSignature fail...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage(), e);
        }
        return null;
    }

    @PostMapping
    public String handleMessage(HttpServletRequest request) {
        try {
            Map<String, String> map = XmlUtil.parseXml(request);
            log.info("handleMessage message -> " + map.toString());
            String output = wechatService.handleMessage(map);
            log.info("handleMessage output -> " + output);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage(), e);
        }
        return null;
    }

}


