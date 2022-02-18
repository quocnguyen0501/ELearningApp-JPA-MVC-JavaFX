package services.impl;

import dao.IPostCommentDAO;
import dao.impl.BaseDAO;
import dao.impl.PostCommentDAO;
import models.PostCommentModel;
import models.UserPostModel;
import services.IPostCommentService;

import java.util.List;

public class PostCommentService extends BaseDAO implements IPostCommentService {
    IPostCommentDAO postCommentDAO;

    public PostCommentService() {
        this.postCommentDAO = new PostCommentDAO();
    }

    @Override
    public PostCommentModel insert(PostCommentModel comment) {
        return postCommentDAO.insert(comment);
    }

    @Override
    public PostCommentModel update(PostCommentModel comment) {
        return postCommentDAO.update(comment);
    }

    @Override
    public void delete(PostCommentModel comment) {
        postCommentDAO.delete(comment);
    }

    @Override
    public PostCommentModel selectById(Long id) {
        return postCommentDAO.selectById(id);
    }

    @Override
    public List<PostCommentModel> selectAll() {
        return postCommentDAO.selectAll();
    }

    @Override
    public List<PostCommentModel> selectByPost(UserPostModel post) {
        return postCommentDAO.selectByPost(post);
    }

    @Override
    public Long selectNumberAllComment() {
        return postCommentDAO.selectNumberAllComment();
    }

    @Override
    public Long selectNumberCommentOfPost(UserPostModel post) {
        return postCommentDAO.selectNumberCommentOfPost(post);
    }
}
