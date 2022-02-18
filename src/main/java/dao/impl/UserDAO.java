package dao.impl;

import dao.IUserDAO;
import models.UserModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UserDAO extends BaseDAO implements IUserDAO {

    @Override
    public UserModel insert(UserModel user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(user);
        entityTransaction.commit();

        return user;
    }

    @Override
    public UserModel update(UserModel user) {
        UserModel userToUpdate = selectById(user.getUser_id());

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setDateOfBirth(user.getDateOfBirth());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setAvatar(user.getAvatar());

        entityTransaction.commit();

        return userToUpdate;
    }

    @Override
    public void delete(UserModel user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.remove(user);
        entityTransaction.commit();
    }

    @Override
    public UserModel selectById(Long id) {
        return entityManager.find(UserModel.class, id);
    }

    @Override
    public UserModel selectByUsernameAndPassword(String username, String password) {
        Query query = entityManager.createQuery("SELECT user FROM UserModel user " +
                "WHERE user.username = '" + username +
                "' AND user.password = '" + password + "'");
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (UserModel) query.getSingleResult();
        }
    }

    @Override
    public UserModel selectByUser(String username) {
        Query query = entityManager.createQuery("SELECT user FROM UserModel user " +
                "WHERE user.username = '" + username + "'");

        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (UserModel) query.getSingleResult();
        }
    }

    @Override
    public List<UserModel> selectAll() {
        Query query = entityManager.createQuery("SELECT user FROM UserModel user");

        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }
}
