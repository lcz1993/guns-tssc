package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Bespeak;

import java.util.List;

/**
 * 服务预约Service
 *
 * @author fengshuonan
 * @Date 2018-04-24 10:59:21
 */
public interface IBespeakService {
    List<Bespeak> findList(Bespeak bespeak);
    void insert(Bespeak bespeak);
    int update(Bespeak bespeak);
    int delete(Bespeak bespeak);
    Bespeak get(Bespeak bespeak);
}
