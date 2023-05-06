package com.galio.system.remote;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.utils.ObjectUtil;
import com.galio.system.dto.LoginMemberDto;
import com.galio.system.model.Member;
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

    private final MemberService memberService;

    /**
     * 获取成员信息详细信息
     *
     * @param username 主键
     */
    @GetMapping(value = "/getInfoByUsername")
    public LoginMemberDto getInfoByUsername(@NotNull(message = "用户名不能为空") @RequestParam String username) {
        Member member = memberService.queryByName(username);
        if (member == null){
            member = new Member();
            member.setUsername("test");
            member.setPassword("password");
        }
        return ObjectUtil.copyObject(member,LoginMemberDto.class);
    }

}
