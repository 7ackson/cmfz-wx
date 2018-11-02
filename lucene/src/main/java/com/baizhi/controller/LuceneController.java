package com.baizhi.controller;

import com.baizhi.entity.Product;
import com.baizhi.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lucene")
public class LuceneController {
    @Autowired
    private LuceneService luceneService;

    @RequestMapping("/selectAll")
    public @ResponseBody List<Product> selectAll(String text){
        return luceneService.selectAll(text);
    }

    @RequestMapping("/insert")
    public void insert(Product product){
        luceneService.insert(product);
    }

    @RequestMapping("/delete")
    public void delete(String id){
        luceneService.delete(id);
    }

    @RequestMapping("/update")
    public void update(Product product){
        luceneService.update(product);
    }
}
