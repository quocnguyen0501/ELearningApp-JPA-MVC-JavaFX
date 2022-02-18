package dao.impl;

import dao.IUserPostDAO;
import models.UserModel;
import models.UserPostModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UserPostDAO extends BaseDAO implements IUserPostDAO {
    @Override
    public UserPostModel insert(UserPostModel post) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(post);
        entityTransaction.commit();

        return post;
    }

    @Override
    public UserPostModel update(UserPostModel post) {
        UserPostModel postToUpdate = selectById(post.getId());

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.merge(postToUpdate);
        entityTransaction.commit();

        return postToUpdate;
    }

    @Override
    public void delete(UserPostModel post) {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.remove(post);
        entityTransaction.commit();
    }

    @Override
    public UserPostModel selectById(Long id) {
        return entityManager.find(UserPostModel.class, id);
    }

    @Override
    public List<UserPostModel> selectAll() {
        Query query = entityManager.createQuery("SELECT p FROM UserPostModel p ORDER BY p.id DESC");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<UserPostModel> selectByUser(UserModel user) {
        Query query = entityManager.createQuery("SELECT p FROM UserPostModel p WHERE p.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public Long selectNumberOfAllPost() {
        Query query = entityManager.createQuery("SELECT COUNT (p) FROM UserPostModel p");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long selectNumberOfPostUser(UserModel user) {
        Query query = entityManager.createQuery("SELECT COUNT (p) FROM UserPostModel p WHERE p.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }
}
