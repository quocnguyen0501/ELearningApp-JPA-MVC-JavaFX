package dao.impl;

import dao.IBaseDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static helper.DBConst.PERSISTENCE_UNIST;

public abstract class BaseDAO implements IBaseDAO {
    protected EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIST);
    protected EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
