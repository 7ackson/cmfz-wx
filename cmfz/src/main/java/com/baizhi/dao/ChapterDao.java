package com.baizhi.dao;

import com.baizhi.entity.Chapter;

public interface ChapterDao {

    public void chapterInsert(Chapter chapter);

    public Chapter selectOne(int id);

}
