package com.baizhi.service;

import com.baizhi.entity.Chapter;

public interface ChapterService {
    public void chapterInsert(Chapter chapter);
    public Chapter selectOne(int id);
}
