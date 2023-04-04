package com.galio.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 平台多端枚举
 */
@Getter
@AllArgsConstructor
public enum DeviceType {
    /**
     * pc端
     */
    PC("pc"),

    /**
     * app端
     */
    APP("app"),

    /**
     * 小程序端
     */
    XCX("xcx"),

    /**
     * 数据可视化-大屏端
     */
    DATAV("datav");

    private final String device;
}
