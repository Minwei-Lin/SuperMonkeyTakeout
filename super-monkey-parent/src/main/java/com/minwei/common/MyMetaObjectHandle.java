package com.minwei.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Title: MyMetaObjectHandle
 * @Author linminwei
 * @Package com.minwei.common
 * @Date 2023/5/12 9:45
 * @description: 自定义元数据对象处理器
 */
@Component
@Slf4j
public class MyMetaObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime",new Date());
        metaObject.setValue("createUser",BaseContext.getCurrentId());
        metaObject.setValue("updateUser",BaseContext.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",new Date());
        metaObject.setValue("updateUser",BaseContext.getCurrentId());
    }
}
