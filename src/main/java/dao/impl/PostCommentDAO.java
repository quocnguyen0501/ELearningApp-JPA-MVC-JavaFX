package dao.impl;

import dao.IPostCommentDAO;
import models.PostCommentModel;
import models.UserPostModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PostCommentDAO extends BaseDAO implements IPostCommentDAO {
    @Override
    public PostCommentModel insert(PostCommentModel comment) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(comment);
        entityTransaction.commit();

        return comment;
    }

    @Override
    public PostCommentModel update(PostCommentModel comment) {
        PostCommentModel commentToUpdate = selectById(comment.getId());

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.merge(commentToUpdate);
        entityTransaction.commit();

        return commentToUpdate;
    }

    @Override
    public void delete(PostCommentModel comment) {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.remove(comment);
        entityTransaction.commit();
    }

    @Override
    public PostCommentModel selectById(Long id) {
        return entityManager.find(PostCommentModel.class, id);
    }

    @Override
    public List<PostCommentModel> selectAll() {
        Query query = entityManager.createQuery("SELECT c FROM PostCommentModel c ORDER BY c.id DESC");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<PostCommentModel> selectByPost(UserPostModel post) {
        Query query = entityManager.createQuery("SELECT c FROM PostCommentModel c WHERE c.post.id = " + post.getId());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public Long selectNumberAllComment() {
        Query query = entityManager.createQuery("SELECT COUNT (c) FROM PostCommentModel c");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long selectNumberCommentOfPost(UserPostModel post) {
        Query query = entityManager.createQuery("SELECT COUNT (c) FROM PostCommentModel c WHERE c.post.id = " + post.getId());
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }
}
