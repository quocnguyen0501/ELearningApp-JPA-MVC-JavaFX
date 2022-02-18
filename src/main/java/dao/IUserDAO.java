package dao;

import models.UserModel;

import java.util.List;

public interface IUserDAO extends IBaseDAO{
    UserModel insert(UserModel user);
    UserModel update(UserModel user);
    void delete(UserModel user);

    UserModel selectById(Long id);
    UserModel selectByUsernameAndPassword(String username, String password);
    UserModel selectByUser(String username);

    List<UserModel> selectAll ();
}
