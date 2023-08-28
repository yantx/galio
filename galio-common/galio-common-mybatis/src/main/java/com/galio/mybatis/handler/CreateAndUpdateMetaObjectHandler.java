package com.galio.mybatis.handler;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseEntity;
import com.galio.core.utils.ObjectUtil;
import com.galio.mybatis.enums.MybatisResponseEnum;
import com.galio.satoken.tools.helper.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Field;
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
            if (ObjectUtil.isNotNull(metaObject)){
                Field field = metaObject.getClass().getDeclaredField("appId");
                if (!field.isAnnotationPresent(TableId.class)){
                    this.setFieldValByName("appId", getAppId(), metaObject);
                }

                if (metaObject.getOriginalObject() instanceof BaseEntity) {
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
                }
            }
        }catch (NoSuchFieldException e) {
            log.warn(e.toString());
        }catch (Exception e) {
            log.error(MybatisResponseEnum.SET_FIELD_VALUE_ERROR.getMsg(),e);
            throw new CustomException(MybatisResponseEnum.SET_FIELD_VALUE_ERROR);
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
            log.error(MybatisResponseEnum.SET_FIELD_VALUE_ERROR.getMsg(),e);
            throw new CustomException(MybatisResponseEnum.SET_FIELD_VALUE_ERROR);
        }
    }

    /**
     * 获取登录账号ID
     */
    private Long getMemberId() {
        return LoginHelper.getMemberId();
    }
    /**
     * 获取登录账号所属应用
     */
    private Long getAppId() {
        return LoginHelper.getAppId();
    }

}
