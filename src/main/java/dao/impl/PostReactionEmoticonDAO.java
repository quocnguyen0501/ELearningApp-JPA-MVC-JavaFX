package dao.impl;

import dao.IPostReactionEmoticonDAO;
import models.PostReactionEmoticonModel;
import models.UserModel;
import models.UserPostModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PostReactionEmoticonDAO extends BaseDAO implements IPostReactionEmoticonDAO {
    @Override
    public PostReactionEmoticonModel insert(PostReactionEmoticonModel reactionEmoticon) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(reactionEmoticon);
        entityTransaction.commit();

        return reactionEmoticon;
    }

    @Override
    public PostReactionEmoticonModel update(PostReactionEmoticonModel reactionEmoticon) {
        PostReactionEmoticonModel reactionEmoticonToUpdate = selectById(reactionEmoticon.getId());

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.merge(reactionEmoticonToUpdate);
        entityTransaction.commit();

        return reactionEmoticonToUpdate;
    }

    @Override
    public void delete(PostReactionEmoticonModel reactionEmoticon) {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.remove(reactionEmoticon);
        entityTransaction.commit();
    }

    @Override
    public PostReactionEmoticonModel selectById(Long id) {
        return entityManager.find(PostReactionEmoticonModel.class, id);
    }

    @Override
    public List<PostReactionEmoticonModel> selectAll() {
        Query query = entityManager.createQuery("SELECT r FROM PostReactionEmoticonModel r");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<PostReactionEmoticonModel> selectByPost(UserPostModel post) {
        Query query = entityManager.createQuery("SELECT r FROM PostReactionEmoticonModel r WHERE r.post.id = " + post.getId());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public PostReactionEmoticonModel selectByPostAndUser(UserModel user, UserPostModel post) {
        Query query = entityManager.createQuery("SELECT r FROM PostReactionEmoticonModel r WHERE r.post.id = " + post.getId() + " AND r.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? (PostReactionEmoticonModel) query.getSingleResult() : null;
    }

    @Override
    public Long selectNumberAllReactionEmoticon() {
        Query query = entityManager.createQuery("SELECT COUNT (r) FROM PostReactionEmoticonModel r");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long selectNumberReactionEmoticonOfPost(UserPostModel post) {
        Query query = entityManager.createQuery("SELECT COUNT (r) FROM PostReactionEmoticonModel r WHERE r.post.id = " + post.getId());
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public String getReactionEmoticonByUserAndPost(UserModel user, UserPostModel post) {
        Query query = entityManager.createQuery("SELECT r.reactionEmoticon FROM PostReactionEmoticonModel r WHERE r.post.id = " + post.getId() + " AND r.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? (String) query.getSingleResult() : null;
    }

    @Override
    public Long getNumberReactionEmoticonByUserAndPost(UserModel user, UserPostModel post) {
        Query query = entityManager.createQuery("SELECT COUNT(r) FROM PostReactionEmoticonModel r WHERE r.post.id = " + post.getId() + " AND r.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }
}
