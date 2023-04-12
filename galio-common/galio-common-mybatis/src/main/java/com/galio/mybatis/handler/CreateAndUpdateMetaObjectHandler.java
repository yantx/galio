package com.galio.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseEntity;
import com.galio.core.utils.DateUtil;
import com.galio.core.utils.ObjectUtil;
import com.galio.satoken.utils.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

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
                LocalDateTime current = ObjectUtil.isNotNull(baseEntity.getCreateTime())
                        ? baseEntity.getCreateTime() : LocalDateTime.now().withNano(0);
                baseEntity.setCreateTime(current);
                baseEntity.setUpdateTime(current);
                Long memberId = ObjectUtil.isNotNull(baseEntity.getCreateBy())
                        ? baseEntity.getCreateBy() : getMemberId();
                // 当前已登录 且 创建人为空 则填充
                baseEntity.setCreateBy(memberId);
                // 当前已登录 且 更新人为空 则填充
                baseEntity.setUpdateBy(memberId);

                baseEntity.setAppId(1L);
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
                LocalDateTime current = LocalDateTime.now().withNano(0);
                // 更新时间填充(不管为不为空)
                baseEntity.setUpdateTime(current);
                Long memberId = getMemberId();
                // 当前已登录 更新人填充(不管为不为空)
                if (ObjectUtil.isNotNull(memberId)) {
                    baseEntity.setUpdateBy(memberId);
                }
            }
        } catch (Exception e) {
            throw new CustomException(4401,"自动注入异常 => " + e.getMessage());
        }
    }

    /**
     * 获取登录账号ID
     */
    private Long getMemberId() {
        return LoginHelper.getMemberId();
    }

}
