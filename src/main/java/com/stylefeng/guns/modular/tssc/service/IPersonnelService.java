package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Personnel;

import java.util.List;

/**
 * 人员Service
 *
 * @author fengshuonan
 * @Date 2018-04-16 11:59:01
 */
public interface IPersonnelService {
    /**
     * 查询所有的人员
     * @param personnel
     * @return
     */
    List<Personnel> findList(Personnel personnel);

    /**
     * 修改
     * @param personnel
     */
    void update(Personnel personnel);

    /**
     * 添加人员职位
     * @param personnel
     */
    void insert(Personnel personnel);
}
