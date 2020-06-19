package com.imcode.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imcode.cms.entity.ArticleType;
import com.imcode.cms.service.IArticleTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class ArticleTypeServiceImplTest {

    @Autowired
    private IArticleTypeService articleTypeService;




    @Test
    public void testPage() {
        QueryWrapper query = new QueryWrapper();
        query.orderByAsc("type_id");
        IPage<ArticleType> pageInfo = new Page<>(2,5);
        articleTypeService.page(pageInfo,query);
        
        List<ArticleType> list = pageInfo.getRecords();
        for (ArticleType type : list) {
           // System.out.println(type.getTypeId() + "-->" + type.getName());
        }
        System.out.println("当前页码:" + pageInfo.getCurrent());
        System.out.println("总页数:" + pageInfo.getPages());
        System.out.println("一页显示多少条:" + pageInfo.getSize());
        System.out.println("总条数:" + pageInfo.getTotal());
    }




    @Test
    public void testWrapper() {
        // List<T> list(Wrapper<T> queryWrapper);
        QueryWrapper query = new QueryWrapper();
        //query.like(true,"name","国内");
        query.in("type_id",1,2,3);

        //query.eq("id",1).or().eq("name","老王");
        List<ArticleType> list =  articleTypeService.list(query);
        System.out.println(list);

    }

    @Test
    public void getById() {

        ArticleType type = articleTypeService.getById(1);
        System.out.println(type);
    }

    @Test
    public void deleteById() {
        articleTypeService.removeById(15);
    }
}


