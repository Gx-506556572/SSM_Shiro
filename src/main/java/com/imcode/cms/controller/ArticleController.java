package com.imcode.cms.controller;


import com.imcode.cms.entity.Article;
import com.imcode.cms.service.IArticleService;
import com.imcode.common.model.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-11-01
 */
@Controller
@RequestMapping("/cms/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("/add")
    public String add(){
        return "cms/cms_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public R add(Article article){
        articleService.save(article);
        return R.ok();
    }
}
