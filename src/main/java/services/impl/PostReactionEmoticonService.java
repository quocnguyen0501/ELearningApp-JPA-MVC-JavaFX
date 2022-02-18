package services.impl;

import dao.IPostReactionEmoticonDAO;
import dao.impl.BaseDAO;
import dao.impl.PostReactionEmoticonDAO;
import models.PostReactionEmoticonModel;
import models.UserModel;
import models.UserPostModel;
import services.IPostReactionEmoticonService;

import java.util.List;

public class PostReactionEmoticonService extends BaseDAO implements IPostReactionEmoticonService {
    IPostReactionEmoticonDAO postReactionEmoticonDAO;

    public PostReactionEmoticonService() {
        this.postReactionEmoticonDAO = new PostReactionEmoticonDAO();
    }

    @Override
    public PostReactionEmoticonModel insert(PostReactionEmoticonModel reactionEmoticon) {
        return postReactionEmoticonDAO.insert(reactionEmoticon);
    }

    @Override
    public PostReactionEmoticonModel update(PostReactionEmoticonModel reactionEmoticon) {
        return postReactionEmoticonDAO.update(reactionEmoticon);
    }

    @Override
    public void delete(PostReactionEmoticonModel reactionEmoticon) {
        postReactionEmoticonDAO.delete(reactionEmoticon);
    }

    @Override
    public PostReactionEmoticonModel selectById(Long id) {
        return postReactionEmoticonDAO.selectById(id);
    }

    @Override
    public List<PostReactionEmoticonModel> selectAll() {
        return postReactionEmoticonDAO.selectAll();
    }

    @Override
    public List<PostReactionEmoticonModel> selectByPost(UserPostModel post) {
        return postReactionEmoticonDAO.selectByPost(post);
    }

    @Override
    public PostReactionEmoticonModel selectByPostAndUser(UserModel user, UserPostModel post) {
        return postReactionEmoticonDAO.selectByPostAndUser(user, post);
    }

    @Override
    public Long selectNumberAllReactionEmoticon() {
        return postReactionEmoticonDAO.selectNumberAllReactionEmoticon();
    }

    @Override
    public Long selectNumberReactionEmoticonOfPost(UserPostModel post) {
        return postReactionEmoticonDAO.selectNumberReactionEmoticonOfPost(post);
    }

    @Override
    public String getReactionEmoticonByUserAndPost(UserModel user, UserPostModel post) {
        return postReactionEmoticonDAO.getReactionEmoticonByUserAndPost(user, post);
    }

    @Override
    public Long getNumberReactionEmoticonByUserAndPost(UserModel user, UserPostModel post) {
        return postReactionEmoticonDAO.getNumberReactionEmoticonByUserAndPost(user, post);
    }
}
