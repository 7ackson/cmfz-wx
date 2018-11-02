package com.baizhi.dao;

import com.baizhi.entity.Product;

import java.util.List;

public interface LuceneDao {
    //查询
    public List<Product> selectAll(String text);

    //删除
    public void delete(String id);

    //修改
    public void update(Product product);

    //添加
    public void insert(Product product);

}
