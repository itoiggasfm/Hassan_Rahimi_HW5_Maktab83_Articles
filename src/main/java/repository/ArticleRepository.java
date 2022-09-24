package repository;

import connection.*;
import entity.*;
import java.sql.*;
import java.time.LocalDateTime;


public class ArticleRepository {

    private static final String INSERT_QUERY = "INSERT INTO articles (title, brief, content, is_published, create_date, user_id) values (?, ?, ?, ?, ?, ?)";
    public void insertArticle(Article article){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1 , article.getTitle());
            preparedStatement.setString(2 , article.getBrief());
            preparedStatement.setString(3 , article.getContent());
            preparedStatement.setBoolean(4 , article.getIsPublished());
            preparedStatement.setDate(5 , java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            preparedStatement.setInt(6 , article.getUserID());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    // private static  final String DELETE_QUERY = "delete from customer where idCustomer = ?" ;
    // public void deleteCustomer(int id){
    //     try{
    //         Connection connection = DataBaseConnection.getInstance();
    //         PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
    //         preparedStatement.setInt(1 , id);

    //         preparedStatement.executeUpdate();
    //         preparedStatement.close();

    //     }
    //     catch (Exception e){
    //         System.out.println("id not found");
    //         e.printStackTrace();
    //     }
    // }

    private static  final String SELECT_IS_PUBLISHED_ARTICLES = "select articles.id, articles.title, articles.brief, articles.content, articles.create_date, users.username from articles left join users on articles.user_id = users.id where articles.is_published = true" ;
    public void findArticle(String searchCase){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IS_PUBLISHED_ARTICLES);
            //preparedStatement.setString(1 , username);

            ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()){
                    if (rs.getString("title").contains(searchCase) ||
                            rs.getString("brief").contains(searchCase) ||
                            rs.getString("content").contains(searchCase)){
                        System.out.println("Article id   : "+rs.getInt("id"));
                        System.out.println("Article title: "+rs.getString("title"));
                        System.out.println("Article brief: "+rs.getString("brief"));
                        System.out.println("Creation Date: "+rs.getString("create_date"));
                        System.out.println("Author name  : "+rs.getString("username"));
                    }
                    else
                        System.out.println("Nothing found.");
                }



            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAllArticles(){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IS_PUBLISHED_ARTICLES);
            //preparedStatement.setString(1 , username);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println("Article id   : "+rs.getInt("id"));
                System.out.println("Article title: "+rs.getString("title"));
                System.out.println("Article brief: "+rs.getString("brief"));
                System.out.println("Creation Date: "+rs.getString("create_date"));
                System.out.println("Author name  : "+rs.getString("username"));
            }
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private static  final String SELECT_MY_ARTICLES = "select id, title, brief, content, create_date from articles  where user_id = ?" ;
    public void findMyArticle(String searchCase, int userID){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MY_ARTICLES);
            preparedStatement.setInt(1 , userID);

            ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()){
                    if (rs.getString("title").contains(searchCase) ||
                            rs.getString("brief").contains(searchCase) ||
                            rs.getString("content").contains(searchCase)){
                        System.out.println("Article id   : "+rs.getInt("id"));
                        System.out.println("Article title: "+rs.getString("title"));
                        System.out.println("Article brief: "+rs.getString("brief"));
                        System.out.println("Creation Date: "+rs.getString("create_date"));
                    }
                    else
                        System.out.println("Nothing found");

                }
            ;
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static  final String SELECT_ALL_MY_ARTICLES = "select id, title, brief, create_date from articles where user_id = ?" ;
    public void showAllMyArticles(int userID){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MY_ARTICLES);
            preparedStatement.setInt(1 , userID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println("Article id   : "+rs.getInt("id"));
                System.out.println("Article title: "+rs.getString("title"));
                System.out.println("Article brief: "+rs.getString("brief"));
                System.out.println("Creation Date: "+rs.getString("create_date"));
            }
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static  final String SELECT_ARTICLE_CONTENT = "select title, content from articles where id = ?" ;
    public void showArticleContent(String articleID){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_CONTENT);
            preparedStatement.setInt(1 , Integer.parseInt(articleID));

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println("Article title: "+rs.getString("title"));
                System.out.println("Content:"+rs.getString("content"));
            }
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static  final String UPDATE_BRIEF_CONTENT = "update articles set brief = ?, content = ? where id = ?" ;
    public void updateBriefContent(String articleID, String newBrief, String newContent){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BRIEF_CONTENT);
            preparedStatement.setString(1 , newBrief);
            preparedStatement.setString(2 , newContent);
            preparedStatement.setInt(3 , Integer.parseInt(articleID));

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private static  final String UPDATE_CONTENT = "update articles set content = ? where id = ?" ;
    public void updateContent(String articleID, String newContent){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTENT);
            preparedStatement.setString(1 , newContent);
            preparedStatement.setInt(2 , Integer.parseInt(articleID));

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private static  final String UPDATE_BRIEF = "update articles set brief = ? where id = ?" ;
    public void updateBrief(String articleID, String newBrief){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BRIEF);
            preparedStatement.setString(1 , newBrief);
            preparedStatement.setInt(2 , Integer.parseInt(articleID));

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
