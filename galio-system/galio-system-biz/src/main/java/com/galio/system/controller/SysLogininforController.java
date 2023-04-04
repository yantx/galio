package com.galio.system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 系统访问日志接口
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sysLogininfor")
public class SysLogininforController {
}
