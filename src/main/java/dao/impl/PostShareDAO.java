package dao.impl;

import dao.IPostShareDAO;
import models.PostShareModel;
import models.UserPostModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PostShareDAO extends BaseDAO implements IPostShareDAO {
    @Override
    public PostShareModel insert(PostShareModel share) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(share);
        entityTransaction.commit();

        return share;
    }

    @Override
    public PostShareModel update(PostShareModel share) {
        PostShareModel shareToUpdate = selectById(share.getId());

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.merge(shareToUpdate);
        entityTransaction.commit();

        return shareToUpdate;
    }

    @Override
    public void delete(PostShareModel share) {

    }

    @Override
    public PostShareModel selectById(Long id) {
        return entityManager.find(PostShareModel.class, id);
    }

    @Override
    public List<PostShareModel> selectAll() {
        Query query = entityManager.createQuery("SELECT s FROM PostShareModel s");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<PostShareModel> selectByPost(UserPostModel post) {
        Query query = entityManager.createQuery("SELECT s FROM PostShareModel s WHERE s.post.id = " + post.getId());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public Long selectNumberAllShare() {
        Query query = entityManager.createQuery("SELECT COUNT(s) FROM PostShareModel s");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long selectNumberShareOfPost(UserPostModel post) {
        Query query = entityManager.createQuery("SELECT COUNT(s) FROM PostShareModel s WHERE s.post.id = " + post.getId());
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }
}
