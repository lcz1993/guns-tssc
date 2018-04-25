package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Recruit;

import java.util.List;

/**
 * 招聘Service
 *
 * @author fengshuonan
 * @Date 2018-04-24 17:08:26
 */
public interface IRecruitService {
    List<Recruit> findList(Recruit recruit);
    void insert(Recruit recruit);
    int update(Recruit recruit);
    int delete(Recruit recruit);
    Recruit get(Recruit recruit);
}
