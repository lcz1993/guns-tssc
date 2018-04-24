package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.tssc.dao.StudioDao;
import com.stylefeng.guns.modular.tssc.entity.Studio;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.IStudioService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.stylefeng.guns.core.util.ToolUtil.isEmpty;

/**
 * 工作室Dao
 *
 * @author fengshuonan
 * @Date 2018-04-10 10:57:11
 */
@Service
public class StudioServiceImpl implements IStudioService {
    @Resource
    private StudioDao studioDao;

    @Override
    public List<Studio> findList(Studio studio) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",studio.getName());
        map.put("founddate",studio.getFounddate());
        return studioDao.selectByMap(map);
    }

    @Override
    public void insert(Studio studio) {
        if (isEmpty(studio.getId())){
            studio.setId(ToolUtil.getUid());
        }
        studioDao.insert(studio);
    }

    @Override
    public void update(Studio studio) {
        studioDao.updateById(studio);
    }

    @Override
    public void delete(Studio studio) {
        studioDao.deleteById(studio.getId());
    }

    @Override
    public Studio get(String id) {
        return studioDao.selectById(id);
    }
}
