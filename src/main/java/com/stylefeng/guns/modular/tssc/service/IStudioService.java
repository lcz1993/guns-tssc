package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Studio;

import java.util.List;

/**
 * 工作室Service
 *
 * @author fengshuonan
 * @Date 2018-04-10 10:57:11
 */
public interface IStudioService{
    /**
     * 查询所有的
     * @param studio
     * @return
     */
    List<Studio> findList(Studio studio);

    /**
     * 新增工作室
     * @param studio
     */
    void insert(Studio studio);

    /**
     * 修改工作室，根据ID
     * @param studio
     */
    void update(Studio studio);

    /**
     * 删除工作室
     * @param studio
     */
    void delete(Studio studio);
    /**
     * 删除工作室
     * @param id
     */
    Studio get(String id);
}
