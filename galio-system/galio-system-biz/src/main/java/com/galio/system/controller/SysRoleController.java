package com.galio.system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 角色相关接口
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
}
