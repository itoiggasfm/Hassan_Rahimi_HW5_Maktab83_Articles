package repository;

import java.sql.*;
import connection.*;
import entity.User;
public class UserRepository {

    private static final String INSERT_QUERY = "INSERT INTO users (username, password, national_code, birthday) values (?, ?, ?, ?)";
    public int insertUser(User user){
        int result=0;
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1 , user.getUsername().toLowerCase());
            preparedStatement.setString(2 , user.getPassword());
            preparedStatement.setString(3 , user.getNationalCOde());
            preparedStatement.setString(4 , user.getBirthday());

            result = preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private static final String GET_USER_ID = "SELECT id FROM users WHERE username = ?";
    public int getUserID(String username){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ID);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return rs.getInt("id");
            preparedStatement.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    private static  final String CHECK_USERNAME_BY_USERNAME = "select username from USERS where username = ?" ;
    public Boolean checkUserByUsername(String username){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_BY_USERNAME);
            preparedStatement.setString(1 , username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return true;
            preparedStatement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static  final String CHECK_USERNAME_BY_OLD_PASSWORD = "select username from USERS where username = ? and password = ?" ;
    public Boolean checkUserByOldPassword(String username, String oldPassword){
        try{
//            System.out.println(username+" / "+oldPassword);
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_BY_OLD_PASSWORD);
            preparedStatement.setString(1 , username);
            preparedStatement.setString(2 , oldPassword);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return true;
            preparedStatement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static  final String CHANGE_PASSWORD = "update USERS set password = ? where username = ?" ;
    public Boolean updatePassword(String username, String newPassword){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);
            preparedStatement.setString(1 , newPassword);
            preparedStatement.setString(2 , username);

            if (preparedStatement.executeUpdate()>0)
                return true;
            preparedStatement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static  final String SIGNIN_USERNAME_PASSWORD = "select username, password from USERS where username = ? and password = ?" ;
    public Boolean checkSignIn(String username, String password){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SIGNIN_USERNAME_PASSWORD);
            preparedStatement.setString(1 , username);
            preparedStatement.setString(2 , password);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return true;
            preparedStatement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
