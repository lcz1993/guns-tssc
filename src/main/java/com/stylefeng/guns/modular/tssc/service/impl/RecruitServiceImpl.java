package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.tssc.dao.RecruitDao;
import com.stylefeng.guns.modular.tssc.entity.Recruit;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.IRecruitService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 招聘Dao
 *
 * @author fengshuonan
 * @Date 2018-04-24 17:08:26
 */
@Service
public class RecruitServiceImpl implements IRecruitService {
    @Resource
    private RecruitDao recruitDao;

    @Override
    public List<Recruit> findList(Recruit recruit) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",recruit.getName());
        map.put("content",recruit.getContent());
        map.put("category",recruit.getCategory());
        map.put("treatment",recruit.getTreatment());
        map.put("nature",recruit.getNature());
        map.put("place",recruit.getPlace());
        map.put("number",recruit.getNumber());
        map.put("interval",recruit.getInterval());
        map.put("vacation",recruit.getVacation());
        map.put("working",recruit.getWorking());
        map.put("condition",recruit.getCondition());
        return recruitDao.selectByMap(map);
    }

    @Override
    public void insert(Recruit recruit) {
        if(ToolUtil.isEmpty(recruit.getId())){
            recruit.setId(ToolUtil.getUid());
        }
        recruitDao.insert(recruit);
    }

    @Override
    public int update(Recruit recruit) {
        return recruitDao.updateById(recruit);
    }

    @Override
    public int delete(Recruit recruit) {
        return recruitDao.deleteById(recruit.getId());
    }

    @Override
    public Recruit get(Recruit recruit) {
        return recruitDao.selectById(recruit.getId());
    }
}
