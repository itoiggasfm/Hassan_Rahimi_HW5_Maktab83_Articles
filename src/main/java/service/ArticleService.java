package service;

import entity.Article;
import repository.*;

public class ArticleService {


    
    private ArticleRepository articleRepository;
    

    
    public ArticleService() {
        this.articleRepository = new ArticleRepository();
    }

//    public void createUser(User user) {
//        userRepository.createUser(user);
//    }

    // public UserService() {
    //     UserService = new UserService();
    // }
    // public void createCustomer(User user){
    //     UserService.createCustomer(user);
    // }
    // public void deleteCustomer(int id){
    //     UserService.deleteCustomer(id);
    // }

    public void findArticle(String searchCase){
        articleRepository.findArticle(searchCase);
    }

    public void showAllArticles(){
        articleRepository.showAllArticles();
    }

   // private static  final String SELECT_ALL_MY_ARTICLES = "select articles.id, articles.title, articles.brief, users.username from articles left join users on articles.user_id = users.id where is_published = 'true'" ;


    public void findMyArticle(String searchCase, int userID){
        articleRepository.findMyArticle(searchCase, userID);
    }
    public void showAllMyArticles(int userID){
        articleRepository.showAllMyArticles(userID);
    }


    public void showArticleContent(String articleID){
        articleRepository.showArticleContent(articleID);
    }


    public void updateBriefContent(String articleID, String newBrief, String newContent){
        articleRepository.updateBriefContent(articleID, newBrief, newContent);
    }


    public void updateContent(String articleID, String newContent){
        articleRepository.updateContent(articleID, newContent);
    }


    public void updateBrief(String articleID, String newBrief){
        articleRepository.updateBrief(articleID, newBrief);
    }

    public void insertArticle(Article article){
        articleRepository.insertArticle(article);
    }

    // public void updateCustomer(String name , int id){
    //     UserService.updateCustomer(name , id);
    // }
}
