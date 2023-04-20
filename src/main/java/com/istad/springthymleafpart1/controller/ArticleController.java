package com.istad.springthymleafpart1.controller;

import com.istad.springthymleafpart1.model.Article;
import com.istad.springthymleafpart1.model.request.ArticleRequest;
import com.istad.springthymleafpart1.service.ArticleService;
import com.istad.springthymleafpart1.service.AuthorService;
import com.istad.springthymleafpart1.service.FileUploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;


@Controller
public class ArticleController {
    ArticleService articleService;
    FileUploadService fileUploadService;

    AuthorService  authorService;
    @Autowired
    ArticleController
            (
            ArticleService articleService,
            AuthorService authorService,
            FileUploadService fileUploadService
            )
    {
        this.articleService = articleService;
        this.authorService = authorService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/all-article")
    public String getAllArticle(Model model){
        model.addAttribute("allArticles",articleService.getAllArticle());
        return "all-article";
    }

    @GetMapping("/post/{postId}")
    public String getPostById(@PathVariable int postId, Model model){
      model.addAttribute("article",articleService.getArticleById(postId));
        return "viewArticle";

    }
    @GetMapping("/from-new-article")
    public String getAddNew(Model model){
        model.addAttribute("article", new ArticleRequest());
        model.addAttribute("authors", authorService.getAllAuthors());

        return "new-article";
    }
    @PostMapping("/handleAddArticle")

    public String handleAddArticle(@Valid @ModelAttribute("article")   ArticleRequest article, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println("Error has happened!!!");
//            model.addAttribute("article", new ArticleRequest());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "new-article";
        }
        Article newArticle = new Article();
        try {
            String filenames = "http://localhost:8080/images/"+fileUploadService.uploadFile(article.getFile());
//            System.out.println("Filename is: "+filenames);
            newArticle.setImgUrl((filenames));
        }
        catch (Exception e){
            newArticle.setImgUrl("https://i1.wp.com/gelatologia.com/wp-content/uploads/2020/07/placeholder.png");
            System.out.println("Error: "+e.getMessage());
        }

            // mapstruct vs model mapper

            newArticle.setTitle(article.getTitle());
            newArticle.setDescription(article.getDescription());


            // find the article by ID
            newArticle.setAuthor(authorService.getAllAuthors().stream().filter(e -> e.getId() == article.getAuthorID())
                    .findFirst().orElse(null));

            // id , author
            // get the max article id and add the value by 1
            newArticle.setId(articleService.getAllArticle().stream().max(Comparator.comparingInt(Article::getId)).stream().toList().get(0).getId() + 1);

            articleService.addNewArticle(newArticle);
            return "redirect:/all-article";

    }
}

