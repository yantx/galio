package com.galio.system.service.impl;

import com.galio.core.constant.MemberConstants;
import com.galio.core.utils.ObjectUtil;
import com.galio.satoken.utils.LoginHelper;
import com.galio.system.dto.EmployeeDto;
import com.galio.system.dto.LoginMemberDto;
import com.galio.system.dto.RoleDto;
import com.galio.system.model.*;
import com.galio.system.repository.*;
import com.galio.system.service.MemberBizService;
import com.galio.system.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: galio
 * @Date: 2023-07-30 08:02:08
 * @Description: 会员关联业务接口实现类 (会员权限)
 */
@RequiredArgsConstructor
@Service
public class MemberBizServiceImpl implements MemberBizService {

    private final MemberService memberService;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final FunctionRepository functionRepository;
    private final GroupRoleRepository groupRoleRepository;
    private final EmployeeRepository employeeRepository;
    private final OrgRepository orgRepository;


    @Override
    public LoginMemberDto queryMemberInfo(String username) {
        Member member = memberService.queryByName(username);
        LoginMemberDto loginMemberDto = ObjectUtil.copyObject(member, LoginMemberDto.class);

        // 角色权限
        List<Role> roleList = roleRepository.selectByMemberId(member.getMemberId());
        List<Group> groupList = groupRepository.selectListByMember(member.getMemberId());
        if (ObjectUtil.isNotEmpty(groupList)){
            Set<Long> groupIds = groupList.stream().map(Group::getGroupId).collect(Collectors.toSet());
            List<GroupRole> roleListWithGroup = groupRoleRepository.selectList(groupIds);
            groupIds = roleListWithGroup.stream().map(GroupRole::getRoleId).collect(Collectors.toSet());
            roleList.addAll(roleRepository.selectList(groupIds));
        }
        loginMemberDto.setRoles(ObjectUtil.copyList(roleList, RoleDto.class));
        if (member.isAdmin() || member.isSuperAdmin()){
            loginMemberDto.setFunctionPermission(Collections.singleton("*.*.*"));
            loginMemberDto.setFunctionPermission(Collections.singleton(MemberConstants.SUPER_ADMIN_ROLE));
        }
        loginMemberDto.setRolePermission(roleList.stream().map(Role::getRoleKey).collect(Collectors.toSet()));

        // 功能权限
        Set<Long> roleIds = roleList.stream().map(Role::getRoleId).collect(Collectors.toSet());
        List<Function> functionList = functionRepository.selectList(roleIds);
        loginMemberDto.setFunctionPermission(functionList.stream().map(Function::getPerms).collect(Collectors.toSet()));

        // 会员号所属雇员信息
        Employee employee = employeeRepository.selectById(member.getEmployeeId());
        loginMemberDto.setEmployee(ObjectUtil.copyObject(employee, EmployeeDto.class));

        // 机构信息
        Org org = orgRepository.selectById(employee.getOrgId());
        loginMemberDto.setOrgId(org.getOrgId());
        loginMemberDto.setOrgName(org.getOrgName());
        if (org.getParentId() != null){
            Org parentOrg = orgRepository.selectById(employee.getOrgId());
            loginMemberDto.setParentOrgName(parentOrg.getOrgName());
        }
        return loginMemberDto;
    }
}
