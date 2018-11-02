package com.baizhi.dao.Impl;

import com.baizhi.dao.LuceneDao;
import com.baizhi.entity.Product;
import com.baizhi.utils.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LuceneDaoImpl implements LuceneDao {
    @Override
    public List<Product> selectAll(String text) {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        //只能根据某一列查询   高级查询
        Query query = new TermQuery(new Term("desc", text));
        List<Product> products = new ArrayList<>();
        try {
            TopDocs topDocs = indexSearcher.search(query, 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                int doc = scoreDocs[i].doc;
                Document document = indexSearcher.doc(doc);
                Product product = getProFromDoc(document);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void delete(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = getDocFormPro(product);
        try {
            indexWriter.updateDocument(new Term("id", product.getId()), document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFormPro = getDocFormPro(product);
        try {
            indexWriter.addDocument(docFormPro);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }


    }

    public Product getProFromDoc(Document document) {
        Product product = new Product();
        product.setId(document.get("id"));
        product.setName(document.get("name"));
        product.setDesc(document.get("desc"));
        product.setUrl(document.get("url"));
        return product;
    }


    public Document getDocFormPro(Product product) {
        Document document = new Document();
        document.add(new StringField("id", product.getId(), Field.Store.YES));
        document.add(new StringField("name", product.getName(), Field.Store.YES));
        document.add(new StringField("desc", product.getDesc(), Field.Store.YES));
        document.add(new StringField("url", product.getUrl(), Field.Store.YES));
        return document;
    }

}
