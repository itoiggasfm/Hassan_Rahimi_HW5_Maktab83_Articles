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

    private static  final String SELECT_USERNAME_BY_USERNAME = "select username from USERS where username = ?" ;
    public Boolean checkUserByUsername(String username){
        try{
            Connection connection = DataBaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERNAME_BY_USERNAME);
            preparedStatement.setString(1 , username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return true;


//            while (rs.next()){
//                // int Id = rs.getInt("idcustomer");
//                if (username.equals(rs.getString("username"))) {
//                    System.out.println("This UserName Already Exist!");
//                }
//                else{
//
//                }
//
//                // System.out.println(username+" / " );
//            }
            preparedStatement.close();

        }
        catch (Exception e){
            //System.out.println("id not found");
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


//            while (rs.next()){
//                // int Id = rs.getInt("idcustomer");
//                if (username.equals(rs.getString("username"))) {
//                    System.out.println("This UserName Already Exist!");
//                }
//                else{
//
//                }
//
//                // System.out.println(username+" / " );
//            }
            preparedStatement.close();

        }
        catch (Exception e){
            //System.out.println("id not found");
            e.printStackTrace();
        }
        return false;
    }

    // private static  final String UPDATE_QUERY = "update customer set name = ? where idcustomer = ?" ;
    // public void updateCustomer(String name , int id){
    //     try{
    //         Connection connection = DataBaseConnection.getInstance();
    //         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
    //         preparedStatement.setString(1 , name);
    //         preparedStatement.setInt(2 , id);

    //         preparedStatement.executeUpdate();
    //         preparedStatement.close();

    //     }
    //     catch (Exception e){
    //         System.out.println("id not found");
    //         e.printStackTrace();
    //     }
    // }

}
