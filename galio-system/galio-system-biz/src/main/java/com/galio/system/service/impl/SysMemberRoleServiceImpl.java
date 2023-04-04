package com.galio.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.galio.system.model.SysMemberRole;
import com.galio.system.service.ISysMemberRoleService;
import com.galio.system.mapper.SysMemberRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author txyan
* @description 针对表【sys_member_role(成员和角色关联表)】的数据库操作Service实现
* @createDate 2023-02-15 21:13:37
*/
@Service
public class SysMemberRoleServiceImpl extends ServiceImpl<SysMemberRoleMapper, SysMemberRole>
    implements ISysMemberRoleService {

    @Override
    public boolean saveOrUpdate(SysMemberRole entity) {
        return super.saveOrUpdate(entity);
    }
}




