package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.modular.tssc.dao.BespeakDao;
import com.stylefeng.guns.modular.tssc.entity.Bespeak;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.IBespeakService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务预约Dao
 *
 * @author fengshuonan
 * @Date 2018-04-24 10:59:21
 */
@Service
public class BespeakServiceImpl implements IBespeakService {

    @Resource
    private BespeakDao bespeakDao;

    @Override
    public List<Bespeak> findList(Bespeak serviceOrder) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",serviceOrder.getName());
        map.put("title",serviceOrder.getTitle());
        map.put("email",serviceOrder.getEmail());
        map.put("tel",serviceOrder.getTel());
        map.put("companyName",serviceOrder.getCompanyName());
        map.put("companyWeb",serviceOrder.getCompanyWeb());
        map.put("budget",serviceOrder.getBudget());
        map.put("brand",serviceOrder.getBrand());
        map.put("service",serviceOrder.getService());
        map.put("remark",serviceOrder.getRemark());
        map.put("isaccept",serviceOrder.getIsaccept());
        return bespeakDao.selectByMap(map);
    }

    @Override
    public void insert(Bespeak serviceOrder) {
        bespeakDao.insert(serviceOrder);
    }

    @Override
    public int update(Bespeak serviceOrder) {
        return bespeakDao.updateById(serviceOrder);
    }

    @Override
    public int delete(Bespeak serviceOrder) {
        return bespeakDao.deleteById(serviceOrder);
    }

    @Override
    public Bespeak get(Bespeak serviceOrder) {
        return bespeakDao.selectById(serviceOrder.getId());
    }
}
