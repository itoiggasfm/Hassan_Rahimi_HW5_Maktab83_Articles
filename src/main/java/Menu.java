import java.util.Scanner;
import entity.*;
import service.*;

public class Menu {

    public static void home() {
        Scanner input = new Scanner(System.in);
        String Choice = new String();
        boolean flag = true;
        while (flag) {
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Articles");
            System.out.println("4. Exit");
            Choice = input.next();
            switch (Choice) {
                case ("1"):
                    signIn();
                    break;
                case ("2"):
                    signUp();
                    break;
                case ("3"):
                    System.out.println("Good BUY");
                    flag = false;
                    break;
                case ("4"):
                    System.out.println("Hope to see you again.");
                    flag = false;
                    break;
                default:
                    System.out.println("INVALID COMMAND");
                    break;
            }
        }
    }

    public static void signUp() {
        Scanner input = new Scanner(System.in);
        UserService userService = new UserService();
        User user = new User();

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

        // System.out.println("Enter Your Password");
        // String passwordThe1st = new String(input.next());

        // System.out.println("Enter Your Password Again");
        // String passwordThe2de = new String(input.next());

        // while(!user.checkPassWord(passwordThe1st, passwordThe2de)){
        //     System.out.println("Enter Your Password");
        //     passwordTheFirst = new String(input.next());

        //     System.out.println("Enter Your Password Again");
        //     passwordTheSeconde = new String(input.next());
        // } 
        user.setPassword(nationalCode);

        System.out.print("Enter your birthday(Please enter the birthday in the form of 1400/01/01): ");
        String birthday = new String(input.next());
        while (!user.checkBirthday(birthday)) {
            System.out.print("Enter your birthday(Please enter the birthday in the form of 1400/01/01): ");
            birthday = new String(input.next());
        }
        user.setBirthday(birthday);

        if (userService.insertUser(user) > 0) {
            System.out.println("Welcome dear " + user.getUsername());
            myProfile();
        }
    }

    public static void signIn() {
        Scanner input = new Scanner(System.in);
        UserService userService = new UserService();
        User user = new User();

        System.out.print("Enter your username: ");
        String username = new String(input.next().toLowerCase());
        System.out.print("Enter your password(Default password is your national code): ");
        String password = new String(input.next());

        if (userService.checkSignIn(username, password)) {
            System.out.println("Welcome dear " + user.getUsername());
            user.setUsername(username);
            user.setPassword(password);
            myProfile();
        }
        else{
            System.out.println("Username or password is incorrect.");
            System.out.println();
        }
    }

    public static void myProfile(){
        Scanner input = new Scanner(System.in);
        String Choice = new String();
        boolean flag = true;
        while (flag) {
            System.out.println("1. My Articles");
            System.out.println("2. Edit article");
            System.out.println("3. New article");
            System.out.println("4. Sign out");
            Choice = input.next();
            switch (Choice) {
                case ("1"):
                    System.out.println("in");
                    break;
                case ("2"):
                    signUp();
                    break;
                case ("3"):
                    System.out.println("Good BUY");
                    flag = false;
                    break;
                case ("4"):
                    home();
                    break;
                default:
                    System.out.println("INVALID COMMAND");
                    break;
            }
        }
    }
}


