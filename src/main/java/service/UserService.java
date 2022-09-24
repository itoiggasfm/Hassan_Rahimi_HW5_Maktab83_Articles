package service;

import entity.User;
import repository.*;

public class UserService {
    private UserRepository userRepository;
    public UserService() {
        this.userRepository = new UserRepository();
    }
  public int insertUser(User user) {
       return  userRepository.insertUser(user);
    }
    // public UserService() {
    //     UserService = new UserService();
    // }
    // public void createCustomer(User user){
    //     UserService.createCustomer(user);
    // }
    // public void deleteCustomer(int id){
    //     UserService.deleteCustomer(id);
    // }


    public int getUserID(String username){
        return userRepository.getUserID(username);
    }
public boolean checkUserByUsername(String username){
        return userRepository.checkUserByUsername(username);
    }

    public boolean checkUserByOldPassword(String username, String oldPassword){
        return userRepository.checkUserByOldPassword(username, oldPassword);
    }


    public boolean updatePassword(String username, String newPassword){
        return userRepository.updatePassword(username, newPassword);
    }


        // public void updateCustomer(String name , int id){
    //     UserService.updateCustomer(name , id);
    // }
    public boolean checkSignIn(String username, String password){
return userRepository.checkSignIn(username, password);

}


}