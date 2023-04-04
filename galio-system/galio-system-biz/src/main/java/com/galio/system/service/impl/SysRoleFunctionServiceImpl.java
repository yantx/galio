package com.galio.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.galio.system.model.SysRoleFunction;
import com.galio.system.service.ISysRoleFunctionService;
import com.galio.system.mapper.SysRoleFunctionMapper;
import org.springframework.stereotype.Service;

/**
* @author txyan
* @description 针对表【sys_role_function(角色和功能关联表)】的数据库操作Service实现
* @createDate 2023-02-15 21:13:37
*/
@Service
public class SysRoleFunctionServiceImpl extends ServiceImpl<SysRoleFunctionMapper, SysRoleFunction>
    implements ISysRoleFunctionService{

}




