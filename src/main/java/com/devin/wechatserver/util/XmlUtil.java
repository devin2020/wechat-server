package com.devin.wechatserver.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml工具
 *
 * @author devin
 */
public class XmlUtil {

    private static final String PREFIX_XML = "<xml>";

    private static final String SUFFIX_XML = "</xml>";

    private static final String PREFIX_CDATA = "<![CDATA[";

    private static final String SUFFIX_CDATA = "]]>";

    /**
     * 消息转为map
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        return map;
    }

    /**
     * 转化成xml, 单层无嵌套
     *
     * @param isAddCDATA  是否加CDATA标签
     */
    public static String xmlFormat(Map<String, String> map, boolean isAddCDATA) {
        StringBuilder sb = new StringBuilder(PREFIX_XML);
        if (!CollectionUtils.isEmpty(map)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append("<").append(entry.getKey()).append(">");
                if (isAddCDATA) {
                    sb.append(PREFIX_CDATA);
                    if (StringUtils.hasText(entry.getValue())) {
                        sb.append(entry.getValue());
                    }
                    sb.append(SUFFIX_CDATA);
                } else {
                    if (StringUtils.hasText(entry.getValue())) {
                        sb.append(entry.getValue());
                    }
                }
                sb.append("</").append(entry.getKey()).append(">");
            }
        }
        return sb.append(SUFFIX_XML).toString();
    }

}
