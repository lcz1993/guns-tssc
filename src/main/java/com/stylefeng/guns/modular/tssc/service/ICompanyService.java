package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Company;

import java.util.List;

/**
 * 公司Service
 *
 * @author fengshuonan
 * @Date 2018-04-12 15:17:17
 */
public interface ICompanyService {
    /**
     * 查询公司
     * @return
     */
    Company findCompany();

    /**
     * 根据id修改公司信息
     * @param company
     */
    void updateById(Company company);

    /**
     * 根据公司信息获取公司
     * @param company
     * @return
     */
    Company getCompany(Company company);
}
