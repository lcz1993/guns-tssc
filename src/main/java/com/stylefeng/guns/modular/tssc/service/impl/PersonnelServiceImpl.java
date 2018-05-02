package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.modular.tssc.dao.PersonnelDao;
import com.stylefeng.guns.modular.tssc.entity.Personnel;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.IPersonnelService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员Dao
 *
 * @author fengshuonan
 * @Date 2018-04-16 11:59:01
 */
@Service
public class PersonnelServiceImpl implements IPersonnelService {

    @Resource
    private PersonnelDao personnelDao;

    @Override
    public List<Personnel> findList(Personnel personnel) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",personnel.getId());
        map.put("name",personnel.getName());
        map.put("eng_name",personnel.getEngName());
        List<Personnel> list = personnelDao.selectByMap(map);
        return list;
    }

    @Override
    public void update(Personnel personnel) {
        personnelDao.updateById(personnel);
    }

    @Override
    public void insert(Personnel personnel) {
        personnelDao.insert(personnel);
    }

    @Override
    public int delete(String id) {
        return personnelDao.deleteById(id);
    }

    @Override
    public Personnel get(String id) {
        return personnelDao.selectById(id);
    }

    @Override
    public Personnel get(Personnel personnel) {
        return personnelDao.selectById(personnel.getId());
    }
}
