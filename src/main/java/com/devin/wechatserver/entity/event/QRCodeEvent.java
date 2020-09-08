package com.devin.wechatserver.entity.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 扫描二维码事件
 *
 * @author devin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QRCodeEvent extends BaseEvent {

    /**
     * 事件KEY值
     */
    private String EventKey;
    /**
     * 用于换取二维码图片
     */
    private String Ticket;

}
