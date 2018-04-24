package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.modular.tssc.dao.FieldDao;
import com.stylefeng.guns.modular.tssc.entity.Field;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.IFieldService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务领域Dao
 *
 * @author fengshuonan
 * @Date 2018-04-17 16:22:41
 */
@Service
public class FieldServiceImpl implements IFieldService {

    @Resource
    private FieldDao fieldDao;
    @Override
    public List<Field> findList(Field field) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",field.getName());
        map.put("introduce",field.getIntroduce());
        map.put("synopsis",field.getSynopsis());
        List<Field> list = fieldDao.selectByMap(map);
        return list;
    }

    @Override
    public void delete(Field field) {
        fieldDao.deleteById(field.getId());
    }

    @Override
    public void insert(Field field) {
        fieldDao.insert(field);
    }

    @Override
    public void update(Field field) {
        fieldDao.updateById(field);
    }

    @Override
    public Field get(Field field) {
        return fieldDao.selectOne(field);
    }
}
