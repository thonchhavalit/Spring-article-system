package com.istad.springthymleafpart1.repository;

import com.istad.springthymleafpart1.model.Article;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepo {
    private List<Article> articleList= new ArrayList<>(){{
        add(new Article(1001,"R1","new evolution of promt programming","https://th.bing.com/th/id/OIP.bMvMlvlAKfiW-CXVxiUkgQHaE8?w=296&h=197&c=7&r=0&o=5&dpr=1.3&pid=1.7",new AuthorRepo().getAllAuthors().get(0)));
        add(new Article(1002,"R6","make us healthy","https://th.bing.com/th/id/OIP.pBt3pCBuTx5AeCgOCdxg_AHaEt?w=297&h=189&c=7&r=0&o=5&dpr=1.3&pid=1.7",new AuthorRepo().getAllAuthors().get(3)));
        add(new Article(1003,"R3","Java programming is about about oop","https://th.bing.com/th/id/OIP.T1F4JrTDVpZU0a1bWndmvAHaEc?w=281&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7",new AuthorRepo().getAllAuthors().get(2)));
        add(new Article(1004,"R15","Java programming is about about oop","https://th.bing.com/th/id/OIP.T1F4JrTDVpZU0a1bWndmvAHaEc?w=281&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7",new AuthorRepo().getAllAuthors().get(1)));
    }};
public List<Article> getAllArticles(){
    return articleList;
}
public Article getArticleById(int id ){
    return articleList.stream().filter(e->e.getId()==id).findFirst().orElse(null);
}
public void addNewArticle(Article article){
    articleList.add(article);
}

}
