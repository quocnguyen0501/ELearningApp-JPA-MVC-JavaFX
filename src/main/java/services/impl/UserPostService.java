package services.impl;

import dao.IUserPostDAO;
import dao.impl.BaseDAO;
import dao.impl.UserPostDAO;
import models.UserModel;
import models.UserPostModel;
import services.IUserPostService;

import java.util.List;

public class UserPostService extends BaseDAO implements IUserPostService {
    private final IUserPostDAO userPostDAO;

    public UserPostService() {
        this.userPostDAO = new UserPostDAO();
    }

    @Override
    public UserPostModel insert(UserPostModel post) {
        return userPostDAO.insert(post);
    }

    @Override
    public UserPostModel update(UserPostModel post) {
        return userPostDAO.update(post);
    }

    @Override
    public void delete(UserPostModel post) {
        userPostDAO.delete(post);
    }

    @Override
    public UserPostModel selectById(Long id) {
        return userPostDAO.selectById(id);
    }

    @Override
    public List<UserPostModel> selectAll() {
        return userPostDAO.selectAll();
    }

    @Override
    public List<UserPostModel> selectByUser(UserModel user) {
        return userPostDAO.selectByUser(user);
    }

    @Override
    public Long selectNumberOfAllPost() {
        return userPostDAO.selectNumberOfAllPost();
    }

    @Override
    public Long selectNumberOfPostUser(UserModel user) {
        return userPostDAO.selectNumberOfPostUser(user);
    }
}
