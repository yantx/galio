package com.galio.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseEntity;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
import com.galio.satoken.utils.LoginHelper;
import com.galio.system.dto.LoginMemberDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @Author: galio
 * @Date: 2023-01-31
 * @Description: MP注入处理器
 */
@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = ObjectUtil.isNotNull(baseEntity.getCreateTime())
                        ? baseEntity.getCreateTime() : new Date();
                baseEntity.setCreateTime(current);
                baseEntity.setUpdateTime(current);
                String username = StringUtil.isNotBlank(baseEntity.getCreateBy())
                        ? baseEntity.getCreateBy() : getUsername();
                // 当前已登录 且 创建人为空 则填充
                baseEntity.setCreateBy(username);
                // 当前已登录 且 更新人为空 则填充
                baseEntity.setUpdateBy(username);
            }
        } catch (Exception e) {
            throw new CustomException(4401,"自动注入异常 => " + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = new Date();
                // 更新时间填充(不管为不为空)
                baseEntity.setUpdateTime(current);
                String username = getUsername();
                // 当前已登录 更新人填充(不管为不为空)
                if (StringUtil.isNotBlank(username)) {
                    baseEntity.setUpdateBy(username);
                }
            }
        } catch (Exception e) {
            throw new CustomException(4401,"自动注入异常 => " + e.getMessage());
        }
    }

    /**
     * 获取登录用户名
     */
    private String getUsername() {
        LoginMemberDto loginMember;
        try {
            loginMember = LoginHelper.getLoginMember();
        } catch (Exception e) {
            log.warn("自动注入警告 => 用户未登录");
            return null;
        }
        return loginMember.getUsername();
    }

}
