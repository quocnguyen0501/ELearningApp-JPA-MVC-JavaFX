package services;

import models.UserModel;

import java.util.List;

public interface IUserService {
    UserModel insert(UserModel user);
    UserModel update(UserModel user);
    void delete(UserModel userModel);

    List<UserModel> selectAll ();
    UserModel selectById(Long id);
    UserModel selectByUsernameAndPassword(String username, String password);
    UserModel selectByUser(String username);

    boolean isExist(String username);

    String register(UserModel user);
    String getHashPassword(String password);

    void close ();
}
