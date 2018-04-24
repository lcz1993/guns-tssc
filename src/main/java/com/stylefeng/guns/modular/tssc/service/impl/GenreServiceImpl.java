package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.modular.tssc.dao.GenreDao;
import com.stylefeng.guns.modular.tssc.entity.Genre;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.IGenreService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品类别Dao
 *
 * @author fengshuonan
 * @Date 2018-04-19 17:07:35
 */
@Service
public class GenreServiceImpl implements IGenreService {

    @Resource
    private GenreDao genreDao;

    @Override
    public List<Genre> findList(Genre genre) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",genre.getName());
        return genreDao.selectByMap(map);
    }

    @Override
    public Genre get(Genre genre) {
        return genreDao.selectOne(genre);
    }

    @Override
    public void insert(Genre genre) {
        genreDao.insert(genre);
    }

    @Override
    public void delete(Genre genre) {
        genreDao.deleteById(genre.getId());
    }

    @Override
    public void update(Genre genre) {
        genreDao.updateById(genre);
    }
}
