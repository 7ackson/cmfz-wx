package com.baizhi.dao;

import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterDao {

    public void chapterInsert(Chapter chapter);

    public Chapter selectOne(int id);

    public List<Chapter> selectTwo(String uuid);

}
