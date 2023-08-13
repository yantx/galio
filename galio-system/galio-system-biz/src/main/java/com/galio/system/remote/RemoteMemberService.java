package com.galio.system.remote;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.utils.Assert;
import com.galio.system.dto.LoginMemberDto;
import com.galio.system.service.MemberBizService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: galio
 * @Date: 2023-04-18 07:43:30
 * @Description: 人员相关业务
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/remote/member")
public class RemoteMemberService {

    private final MemberBizService memberBizService;

    /**
     * 获取成员信息详细信息
     *
     * @param username 登录名
     */
    @GetMapping(value = "/getInfoByUsername")
    public LoginMemberDto getInfoByUsername(@NotNull(message = "登录名不能为空") @RequestParam String username) {
        LoginMemberDto member = memberBizService.queryMemberInfo(username);
        Assert.notNull(member, ResponseEnum.MEMBER_NOT_EXITS.withArgs(username));
        return member;
    }

}
