package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.modular.tssc.dao.CompanyDao;
import com.stylefeng.guns.modular.tssc.entity.Company;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.ICompanyService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司Dao
 *
 * @author fengshuonan
 * @Date 2018-04-12 15:17:17
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Resource
    private CompanyDao companyDao;

    @Override
    public Company findCompany() {
        Company company = new Company();
        company.setId("1");
        return companyDao.selectById(company);
    }

    @Override
    public void updateById(Company company) {
        companyDao.updateById(company);
    }

    @Override
    public Company getCompany(Company company) {
        return companyDao.selectById(company);
    }
}
