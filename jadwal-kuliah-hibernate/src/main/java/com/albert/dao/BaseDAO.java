package com.albert.dao;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import com.albert.database.PostgresDatabase;
import com.albert.model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class BaseDAO<T extends BaseEntity> {

  protected Class<T> entityClass;

  public BaseDAO() {
    super();
  }

  public Query<T> createQuery(String stringQuery, Session session) {
    return session.createQuery(stringQuery, entityClass);
  }

  public void delete(T entity) {
    Session session = PostgresDatabase.getSession();
    Transaction transaction = PostgresDatabase.getTransaction(session);
    session.delete(entity);
    transaction.commit();
    session.close();
  }

  @Transactional
  public T findById(UUID id) {
    Session session = PostgresDatabase.getSession();
    T t = session.get(entityClass, id);
    session.close();
    return t;
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    Session session = PostgresDatabase.getSession();
    List<T> list = session.createQuery("from " + entityClass.getName()).list();
    session.close();
    return list;
  }

  @Transactional
  public void save(T entity) {
    Session session = PostgresDatabase.getSession();
    Transaction transaction = PostgresDatabase.getTransaction(session);
    if (entity.getId() == null) {
      entity.setId(UUID.randomUUID());
    }
    session.save(entity);
    transaction.commit();
    session.close();
  }

  protected void setEntityClass(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public void update(T entity) {
    Session session = PostgresDatabase.getSession();
    Transaction transaction = PostgresDatabase.getTransaction(session);
    session.update(entity);;
    transaction.commit();
    session.close();
  }
}
