package services.impl;

import dao.IPostShareDAO;
import dao.impl.BaseDAO;
import dao.impl.PostShareDAO;
import models.PostShareModel;
import models.UserPostModel;
import services.IPostShareService;

import java.util.List;

public class PostShareService extends BaseDAO implements IPostShareService {
    IPostShareDAO postShareDAO;

    public PostShareService() {
        this.postShareDAO = new PostShareDAO();
    }

    @Override
    public PostShareModel insert(PostShareModel share) {
        return postShareDAO.insert(share);
    }

    @Override
    public PostShareModel update(PostShareModel share) {
        return postShareDAO.update(share);
    }

    @Override
    public void delete(PostShareModel share) {
        postShareDAO.delete(share);
    }

    @Override
    public PostShareModel selectById(Long id) {
        return postShareDAO.selectById(id);
    }

    @Override
    public List<PostShareModel> selectAll() {
        return postShareDAO.selectAll();
    }

    @Override
    public List<PostShareModel> selectByPost(UserPostModel post) {
        return postShareDAO.selectByPost(post);
    }

    @Override
    public Long selectNumberAllShare() {
        return postShareDAO.selectNumberAllShare();
    }

    @Override
    public Long selectNumberShareOfPost(UserPostModel post) {
        return postShareDAO.selectNumberShareOfPost(post);
    }
}
