package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.modular.tssc.dao.NewsDao;
import com.stylefeng.guns.modular.tssc.entity.News;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.INewsService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新闻Dao
 *
 * @author fengshuonan
 * @Date 2018-04-23 17:12:55
 */
@Service
public class NewsServiceImpl implements INewsService {

    @Resource
    private NewsDao newsDao;

    @Override
    public List<News> findList(News news) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",news.getName());
        map.put("content",news.getContent());
        map.put("synopsis",news.getSynopsis());
        map.put("time",news.getTime());
        return newsDao.selectByMap(map);
    }

    @Override
    public void insert(News news) {
        newsDao.insert(news);
    }

    @Override
    public int update(News news) {
        return newsDao.updateById(news);
    }

    @Override
    public News get(String id) {
        return newsDao.selectById(id);
    }

    @Override
    public int delete(News news) {
        return newsDao.deleteById(news.getId());
    }
}
