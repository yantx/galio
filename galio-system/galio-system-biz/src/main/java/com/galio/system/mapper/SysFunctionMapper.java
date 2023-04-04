package com.galio.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.galio.core.constant.MemberConstants;
import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.model.SysFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 功能表 持久层
 */
public interface SysFunctionMapper extends BaseMapperPlus<SysFunctionMapper, SysFunction, SysFunction> {

    /**
     * 根据用户所有权限
     *
     * @return 权限列表
     */
    List<String> selectMenuPerms();

    /**
     * 根据用户查询系统菜单列表
     *
     * @param queryWrapper 查询条件
     * @return 菜单列表
     */
    List<SysFunction> selectMenuListByUserId(@Param(Constants.WRAPPER) Wrapper<SysFunction> queryWrapper);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    public List<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    default List<SysFunction> selectMenuTreeAll() {
        LambdaQueryWrapper<SysFunction> lqw = new LambdaQueryWrapper<SysFunction>()
            .in(SysFunction::getFunctionType, MemberConstants.TYPE_DIR, MemberConstants.TYPE_MENU)
            .eq(SysFunction::getStatus, MemberConstants.MENU_NORMAL)
            .orderByAsc(SysFunction::getParentId)
            .orderByAsc(SysFunction::getOrderNum);
        return this.selectList(lqw);
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysFunction> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId            角色ID
     * @param menuCheckStrictly 菜单树选择项是否关联显示
     * @return 选中菜单列表
     */
    List<Long> selectMenuListByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);

}
