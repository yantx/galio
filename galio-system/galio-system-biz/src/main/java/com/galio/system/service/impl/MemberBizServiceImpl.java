package com.galio.system.service.impl;

import com.galio.core.constant.MemberConstants;
import com.galio.core.utils.ObjectUtil;
import com.galio.system.dto.EmployeeDTO;
import com.galio.system.dto.LoginMemberDTO;
import com.galio.system.dto.RoleDTO;
import com.galio.system.entity.*;
import com.galio.system.model.vo.MemberVo;
import com.galio.system.repository.*;
import com.galio.system.service.MemberBizService;
import com.galio.system.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public LoginMemberDTO queryMemberInfo(String username) {
        MemberVo member = memberService.getByName(username);
        LoginMemberDTO loginMemberDTO = ObjectUtil.copyObject(member, LoginMemberDTO.class);

        // 角色功能权限
        List<Role> roleList = queryRoleWithMember(member.getMemberId());
        assert loginMemberDTO != null;
        loginMemberDTO.setRoles(ObjectUtil.copyList(roleList, RoleDTO.class));
        // 超管不作限制，管理员所在应用内不做限制
        if (loginMemberDTO.isAdmin() || loginMemberDTO.isSuperAdmin()){
            loginMemberDTO.setFunctionPerms(Collections.singleton("*.*.*"));
            loginMemberDTO.setRolePerms(Collections.singleton(MemberConstants.SUPER_ADMIN_ROLE));
        }else {
            loginMemberDTO.setRolePerms(roleList.stream().map(Role::getRoleKey).collect(Collectors.toSet()));
            Set<Long> roleIds = roleList.stream().map(Role::getRoleId).collect(Collectors.toSet());
            List<Function> functionList = functionRepository.selectByRoles(roleIds);
            loginMemberDTO.setFunctionPerms(functionList.stream().map(Function::getPerms).collect(Collectors.toSet()));
        }

        // 会员号所属雇员信息
        Employee employee = employeeRepository.selectById(member.getEmployeeId());
        loginMemberDTO.setEmployee(ObjectUtil.copyObject(employee, EmployeeDTO.class));

        // 机构信息
        Org org = orgRepository.selectById(employee.getOrgId());
        loginMemberDTO.setOrgId(org.getOrgId());
        loginMemberDTO.setOrgName(org.getOrgName());
        if (org.getParentId() != null){
            Org parentOrg = orgRepository.selectById(employee.getOrgId());
            loginMemberDTO.setParentOrgName(parentOrg.getOrgName());
        }
        return loginMemberDTO;
    }

    /**
     * 获取会员角色
     * @param memberId 会员ID
     * @return 角色集合
     */
    @Override
    public List<Role> queryRoleWithMember(Long memberId){
        List<Role> roleList = roleRepository.selectByMemberId(memberId);
        List<Group> groupList = groupRepository.selectListByMember(memberId);
        if (ObjectUtil.isNotEmpty(groupList)){
            Set<Long> groupIds = groupList.stream().map(Group::getGroupId).collect(Collectors.toSet());
            List<GroupRole> roleListWithGroup = groupRoleRepository.selectList(groupIds);
            groupIds = roleListWithGroup.stream().map(GroupRole::getRoleId).collect(Collectors.toSet());
            roleList.addAll(roleRepository.selectList(groupIds));
        }
        return roleList;
    }
}
