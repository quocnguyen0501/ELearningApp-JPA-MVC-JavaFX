package services.impl;

import dao.impl.BaseDAO;
import dao.impl.UserDAO;
import models.UserModel;
import services.IUserService;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService extends BaseDAO implements IUserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    @Override
    public UserModel insert(UserModel user) {
        return userDAO.insert(user);
    }

    @Override
    public UserModel update(UserModel user) {
        return userDAO.update(user);
    }

    @Override
    public void delete(UserModel user) {
        userDAO.delete(user);
    }

    @Override
    public List<UserModel> selectAll() {
        return userDAO.selectAll();
    }

    @Override
    public UserModel selectById(Long id) {
        return userDAO.selectById(id);
    }

    @Override
    public UserModel selectByUsernameAndPassword(String username, String password) {
        return userDAO.selectByUsernameAndPassword(username, password);
    }

    @Override
    public UserModel selectByUser(String username) {
        return userDAO.selectByUser(username);
    }

    @Override
    public boolean isExist(String username) {
        return this.selectByUser(username) != null;
    }

    @Override
    public String register(UserModel userModel) {
        if (isExist(userModel.getUsername())) {
            return "Your username was exist! Please try again";
        } else {
            this.insert(userModel);
            return null;
        }
    }

    @Override
    public String getHashPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        userDAO.close();
    }
}
