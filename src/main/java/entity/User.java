package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class User{
    private String username;
    private String password;
    private String nationalCOde;
    private String birthday;


    // username setter and getter
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    //paddword setter, checker and getter
    public String getPassword() {
        return password;
    }
    //password checker
    public boolean checkPassWord(String password, String confirmPassword) {
        if (password.equals(confirmPassword)){
           return true;
        }
        else{
            System.out.println("Password does not match.");
            return false;
        }
        
    }
    public void setPassword(String password) {
        this.password = password;
    }


    //national code setter, validator and getter
    public String getNationalCOde() {
        return nationalCOde;
    }
    //national code validator
    public boolean checkNationalCOde(String nationalCOde) {
        if(Pattern.compile("\\d{10}").matcher(nationalCOde).matches()){
            int sum = 0;
            for (int i=10, j=0; i>1; --i)
                sum += Integer.parseInt(String.valueOf(nationalCOde.charAt(j++)))*i;
            if((sum%11<3 && Integer.parseInt(String.valueOf(nationalCOde.charAt(9))) == sum%11) ||
                    (sum%11>=3 && Integer.parseInt(String.valueOf(nationalCOde.charAt(9))) == 11-sum%11) )
                return true;
            else{
                System.out.println("Invalid national code.");
                return false;
            }
        }
        else
            System.out.println("Invalid national code. National code is a 10-digit number.");
            return false;
    }
    public void setNationalCOde(String nationalCOde) {
                this.nationalCOde = nationalCOde;
            }

            //birthday setter, validator and getter
    public String getBirthday() {
        return birthday;
    }
    //birthday validator
    public boolean checkBirthday(String birthday) {
        if(Pattern.compile("\\d{4}/\\d{2}/\\d{2}").matcher(birthday).matches()){
            String[] birthdayParts = birthday.split("/");
            if(Integer.parseInt(birthdayParts[1])>12 || Integer.parseInt(birthdayParts[1])<0){
                System.out.println("Invalid month number. Month number is from 1 to 12.");
                return false;
            }
            if(Integer.parseInt(birthdayParts[1])<=6 && (Integer.parseInt(birthdayParts[2])<0 || Integer.parseInt(birthdayParts[2])>31)){
                System.out.println("Invalid day number. Month " + Integer.parseInt(birthdayParts[1])+ " days are from 1 to 31.");
                return false;
            }
            if((Integer.parseInt(birthdayParts[1])>6 && Integer.parseInt(birthdayParts[1])<12) && (Integer.parseInt(birthdayParts[2])<0 || Integer.parseInt(birthdayParts[2])>30)){
                System.out.println("Invalid day number. Month " + Integer.parseInt(birthdayParts[1]) + " days are from 1 to 30.");
                return false;
            }
            if(Integer.parseInt(birthdayParts[1]) == 12 && (Integer.parseInt(birthdayParts[2])<0 || Integer.parseInt(birthdayParts[2])>29) && !isLeapYear(Integer.parseInt(birthdayParts[0]))){
                System.out.println("Invalid day number. Month " + Integer.parseInt(birthdayParts[1]) + " days are from 1 to 29.");
                return false;
            }
            if(Integer.parseInt(birthdayParts[1]) == 12 && (Integer.parseInt(birthdayParts[2])<0 || Integer.parseInt(birthdayParts[2])>30) && isLeapYear(Integer.parseInt(birthdayParts[0]))){
                System.out.println("Invalid day number. Month " + Integer.parseInt(birthdayParts[1]) + " days are from 1 to 30.");
                return false;
            }
        }
        else{
            System.out.println("Please enter your birthday in the form of 1400/01/01");
            return false;
        }
       //this.birthday = birthday;
        return true;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean checkArticleID(String articleID){
        if(Pattern.compile("[0-9]+").matcher(articleID).matches())
            return true;
        else
            return false;
    }
    //Leap year checker
    public boolean isLeapYear(int year){
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 5, 9, 13, 17, 22,26, 30));
        if(list1.contains(year%33))
            return true;
        else
            return false;
    }
}