package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Field;

import java.util.List;

/**
 * 服务领域Service
 *
 * @author fengshuonan
 * @Date 2018-04-17 16:22:41
 */
public interface IFieldService {
    /**
     * 查询所有服务领域
     * @param field
     * @return
     */
    List<Field> findList(Field field);

    /**
     * 删除服务领域
     * @param field
     */
    void delete(Field field);

    /**
     * 新增服务领域
     * @param field
     */
    void insert(Field field);

    /**
     * 修改服务领域
     * @param field
     */
    void update(Field field);

    /**
     * 根据ID获取唯一field
     * @param field
     * @return
     */
    Field get(Field field);
}
