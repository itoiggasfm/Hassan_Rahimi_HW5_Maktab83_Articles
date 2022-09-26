import java.time.LocalDateTime;
import java.util.Scanner;
import entity.*;
import repository.UserRepository;
import service.*;

public class Menu {

    private  Scanner input = new Scanner(System.in);
    private  User user;
    private Article article;
    private  UserService userService;
    private ArticleService articleService;
    public Menu() {
        userService = new UserService();
        user = new User();
        articleService = new ArticleService();
        article = new Article();
    }


    public  void home() {
//        Scanner input = new Scanner(System.in);
        String choice = new String();
        boolean flag = true;
        while (flag) {
            System.out.println("-----Home Page-----");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Articles");
            System.out.println("4. Exit");
            choice = input.next();
            switch (choice) {
                case ("1"):
                    signIn();
                    break;
                case ("2"):
                    signUp();
                    break;
                case ("3"):
                    articles();
                    System.out.println();
                    System.out.println("Would you like to see the content of articles? (Y/N)");
                    String showContent = input.next().toLowerCase();
                    while(!(showContent.equals("y") ||
                            showContent.equals("n") ||
                            showContent.equals("yes") ||
                            showContent.equals("no"))) {
                        System.out.println("Would you like to see the content of articles? (Y/N) ");
                        showContent = input.next().toLowerCase();
                    }
                    if(showContent.equals("y") || showContent.equals("yes"))
                        articleContent();
                    break;
                case ("4"):
                    System.out.println("Hope to see you again.");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }//end of method home

    public  void signUp() {
//        Scanner input = new Scanner(System.in);
//       UserService userService = new UserService();
//       User user = new User();

        System.out.print("Enter your username: ");
        String username = new String(input.next());
        while (userService.checkUserByUsername(username)) {
            System.out.println("Username already exist.");
            System.out.print("Enter your username: ");
            username = new String(input.next());
        }
        user.setUsername(username);


        System.out.print("Enter your national code: ");
        String nationalCode = new String(input.next());
        while (!user.checkNationalCOde(nationalCode)) {
            System.out.print("Enter your national code: ");
            nationalCode = new String(input.next());
        }
        user.setNationalCOde(nationalCode);

        user.setPassword(nationalCode);

        System.out.print("Enter your birthday(Please enter the birthday in the form of 1400/01/01): ");
        String birthday = new String(input.next());
        while (!user.checkBirthday(birthday)) {
            System.out.print("Enter your birthday(Please enter the birthday in the form of 1400/01/01): ");
            birthday = new String(input.next());
        }
        user.setBirthday(birthday);

        if (userService.insertUser(user) > 0) {
//            System.out.println("Welcome dear " + user.getUsername());
            myProfile();
        }
    }//end of method signUp

    public  void signIn() {
//        Scanner input = new Scanner(System.in);
//        UserService userService = new UserService();
//        User user = new User();

        System.out.print("Enter your username: ");
        String username = input.next().toLowerCase();
        System.out.print("Enter your password(Default password is your national code): ");
        String password = input.next();
//        System.out.println(username+" / "+password);
        if (userService.checkSignIn(username, password)) {
            user.setUsername(username);
            user.setPassword(password);
           // System.out.println("Welcome dear " + user.getUsername());
            myProfile();
        }
        else{
            System.out.println("Username or password is incorrect.");
            System.out.println();
        }
    }//end of method signIn

    public int getUserID(){
         return userService.getUserID(user.getUsername());
    }

    public void myProfile(){
//        UserService userService = new UserService();
//        User user = new User();
//        Scanner input = new Scanner(System.in);
        String choice = new String();
        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("Welcome dear "+user.getUsername());
            System.out.println("1. Change password");
            System.out.println("2. My Articles");
            System.out.println("3. Sign out");
            System.out.println("------------------");
            System.out.println("4. Article");
            choice = input.next();
            switch (choice) {
                case ("1"):
                    changePassword();
                    break;
                case ("2"):
                    myArticles();
                    break;
                case ("3"):
                    flag = false;
                    //home();
                    break;
                case ("4"):
                    articles();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }//end of method myProfile

    public void articles(){
//        ArticleService articleService = new ArticleService();
            System.out.println();
            //System.out.println("Welcome dear "+user.getUsername());
            System.out.println("Choose to search or to see all articles.");
            System.out.println("1. Search ");
            System.out.println("2. All articles ");
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            switch (choice) {
                case ("1"):
                    System.out.println("Search: ");
                    String searchCase = input.next();
                    articleService.findArticle(searchCase);
                    break;
                case ("2"):
                    articleService.showAllArticles();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
    }//end of method articles

    public void changePassword(){
//       UserService userService = new UserService();
//       User user = new User();
//        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome dear "+user.getUsername());
        System.out.println("Old password: ");
        String oldPassword = input.next();
        System.out.println(user.getUsername().toLowerCase());
        if(userService.checkUserByOldPassword(user.getUsername().toLowerCase(), oldPassword)){
            System.out.println("New password: ");
            String newPassword = input.next();
            System.out.println("Confirm password: ");
            String confirmPassword = input.next();
            while (!user.checkPassWord(newPassword, confirmPassword)){
                System.out.println("New password: ");
                newPassword = input.next();
                System.out.println("New password: ");
                confirmPassword = input.next();
            }
            if(userService.updatePassword(user.getUsername().toLowerCase(), newPassword))
                System.out.println("Password changed successfully");;

        }
        else
            System.out.println("Incorrect password.");
    }//end of method changePassword

    public void myArticles(){
//        ArticleService articleService = new ArticleService();
        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("Welcome dear "+user.getUsername());
            System.out.println("Choose to search or to see all articles.");
            System.out.println("1. Back ");
            System.out.println("2. My Articles ");
            System.out.println("3. Edit article ");
            System.out.println("4. New Article ");
            System.out.println("5. Sign out ");
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            switch (choice) {
                case ("1"):
                    flag = false;
                    break;
                case ("2"):
                    searchOrShowMyArticles();
                    System.out.println();
                    System.out.println("Would you like to see the content of articles? (Y/N)");
                    String showContent = input.next().toLowerCase();
                    while(!(showContent.equals("y") ||
                            showContent.equals("n") ||
                            showContent.equals("yes") ||
                            showContent.equals("no"))) {
                        System.out.println("Would you like to see the content of articles? (Y/N) ");
                        showContent = input.next().toLowerCase();
                    }
                    if(showContent.equals("y") || showContent.equals("yes"))
                        articleContent();
                    break;
                case ("3"):
                    editContent();
                    break;
                case ("4"):
                    newArticles();
                    break;
                case ("5"):
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }//end of method myArticles

    public void searchOrShowMyArticles(){
        System.out.println();
        System.out.println("Welcome dear "+user.getUsername());
        System.out.println("Choose to search or to see all your articles.");
        System.out.println("1. Search ");
        System.out.println("2. All my articles ");
        Scanner input = new Scanner(System.in);
        String choice = input.next();
        switch (choice) {
            case ("1"):
                System.out.println("Search: ");
                String searchCase = input.next();
                articleService.findMyArticle(searchCase, getUserID());
                break;
            case ("2"):
                articleService.showAllMyArticles(getUserID());
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void articleContent(){
        System.out.println("Article ID: ");
        String articleID = input.next();
        while(!user.checkArticleID(articleID)){
            System.out.println("Article ID: ");
            articleID = input.next();
        }
        articleService.showArticleContent(articleID);
    }

    public void editContent(){
        System.out.println("Article ID: ");
        String articleID = input.next();
        while(!user.checkArticleID(articleID)){
            System.out.println("Article ID: ");
            articleID = input.next();
        }
        String newBrief = "";
        String newContent = "";
        System.out.println("Would you like to change the brief of articles? (Y/N)");
        String changeBrief = input.next().toLowerCase();
        while(!(changeBrief.equals("y") ||
                changeBrief.equals("n") ||
                changeBrief.equals("yes") ||
                changeBrief.equals("no"))) {
            System.out.println("Would you like to change the brief of articles? (Y/N) ");
            changeBrief = input.next().toLowerCase();
        }
        if(changeBrief.equals("y") || changeBrief.equals("yes")){
            System.out.println("New Brief: ");
            newBrief = input.nextLine();
        }

        System.out.println("Would you like to change the content of articles? (Y/N)");
        String changeContent = input.next().toLowerCase();
        while(!(changeContent.equals("y") ||
                changeContent.equals("n") ||
                changeContent.equals("yes") ||
                changeContent.equals("no"))) {
            System.out.println("Would you like to change the content of articles? (Y/N) ");
            changeContent = input.next();
        }
        if(changeContent.equals("y") || changeContent.equals("yes")){
            System.out.println("New Content: ");
            newContent = input.nextLine();
        }

        if(!newBrief.equals("") && !newContent.equals(""))
            articleService.updateBriefContent(articleID, newBrief, newContent);
        else if(newBrief.equals("") && !newContent.equals(""))
            articleService.updateContent(articleID, newContent);
        else if(!newBrief.equals("") && newContent.equals(""))
            articleService.updateBrief(articleID, newBrief);
    }

    public void newArticles(){
        System.out.println();
        System.out.println("Welcome dear "+user.getUsername());

        article.setUserID(getUserID());

        System.out.println("Your article title: ");
        String myArticleTitle = input.nextLine();
        article.setTitle(myArticleTitle);

        System.out.println("Your article brief: ");
        String myArticleBrief = input.nextLine();
        article.setBrief(myArticleBrief);

        System.out.println("Your article content :");
        String myArticleContent = input.nextLine();
        article.setContent(myArticleContent);

        System.out.println("Would you like your article to be published? (Y/N) ");
        String myArticleIsPublished = input.next().toLowerCase();
        while(!(myArticleIsPublished.equals("y") ||
                myArticleIsPublished.equals("n") ||
                myArticleIsPublished.equals("yes") ||
                myArticleIsPublished.equals("no"))) {
            System.out.println("Would you like your article to be published? (Y/N) ");
            myArticleIsPublished = input.next().toLowerCase();
        }
            if(myArticleIsPublished.equals("y") || myArticleIsPublished.equals("yes"))
                article.setIsPublished(true);
            else if (myArticleIsPublished.equals("n") || myArticleIsPublished.equals("no"))
                article.setIsPublished(false);

        article.setCreateDate(LocalDateTime.now());

        articleService.insertArticle(article);
    }//end of method newArticles


}//end of class menu


