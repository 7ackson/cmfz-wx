package com.baizhi.service.Impl;

import com.baizhi.dao.Impl.LuceneDaoImpl;
import com.baizhi.dao.LuceneDao;
import com.baizhi.entity.Product;
import com.baizhi.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("LucenService")
public class LuceneServiceImpl implements LuceneService {

    private LuceneDao luceneDao = new LuceneDaoImpl();

    @Override
    public List<Product> selectAll(String text) {
        return luceneDao.selectAll(text);
    }

    @Override
    public void delete(String id) {
        luceneDao.delete(id);
    }

    @Override
    public void update(Product product) {
        luceneDao.update(product);
    }

    @Override
    public void insert(Product product) {
        Random random = new Random();
        int i  = random.nextInt(10000);
        String id = String.valueOf(i);
        product.setId(id);
        System.out.println(product);
        luceneDao.insert(product);
    }
}
