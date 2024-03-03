package com.galio.system.remote;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.utils.Assert;
import com.galio.system.dto.LoginMemberDTO;
import com.galio.system.model.vo.MemberVo;
import com.galio.system.service.MemberBizService;
import com.galio.system.service.MemberService;
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
    private final MemberService memberService;

    /**
     * 获取成员信息详细信息
     *
     * @param username 登录名
     */
    @GetMapping(value = "/getInfoByUsername")
    public LoginMemberDTO getInfoByUsername(@NotNull(message = "登录名不能为空") @RequestParam String username) {
        LoginMemberDTO member = memberBizService.queryMemberInfo(username);
        Assert.notNull(member, ResponseEnum.MEMBER_NOT_EXITS.withArgs(username));
        return member;
    }

    @GetMapping(value = "/getPassword")
    public String getPassword(@NotNull(message = "登录名不能为空") @RequestParam String username) {
        MemberVo member = memberService.getByName(username);
        Assert.notNull(member, ResponseEnum.MEMBER_NOT_EXITS.withArgs(username));
        return member.getPassword();
    }

}
