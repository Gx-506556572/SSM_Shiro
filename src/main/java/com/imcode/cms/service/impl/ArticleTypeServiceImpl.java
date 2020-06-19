package com.imcode.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imcode.cms.entity.ArticleType;
import com.imcode.cms.mapper.ArticleTypeMapper;
import com.imcode.cms.service.IArticleTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class ArticleTypeServiceImpl
        extends ServiceImpl<ArticleTypeMapper, ArticleType> implements IArticleTypeService {

    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        super.removeById(id);
        //int i = 100/0;
        return true;
    }
}