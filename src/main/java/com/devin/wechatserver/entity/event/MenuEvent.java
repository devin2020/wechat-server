package com.devin.wechatserver.entity.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义菜单事件
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuEvent extends BaseEvent {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    private String EventKey;

}
