package com.albert.dao;

import java.util.List;
import java.util.UUID;
import com.albert.database.PostgresDatabase;
import com.albert.model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class BaseDAO<T extends BaseEntity> {

  protected PostgresDatabase postgresDatabase;
  protected Class<T> entityClass;

  public BaseDAO(PostgresDatabase postgresDatabase) {
    super();
    this.postgresDatabase = postgresDatabase;
  }

  public Query<T> createQuery(String stringQuery) {
    return postgresDatabase.getSession().createQuery(stringQuery, entityClass);
  }

  public void delete(T entity) {
    Session session = postgresDatabase.getSession();
    Transaction transaction = postgresDatabase.getTransaction(session);
    session.delete(entity);
    transaction.commit();
  }

  public T findById(UUID id) {
    return postgresDatabase.getSession().get(entityClass, id);
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    return postgresDatabase.getSession().createQuery("from " + entityClass.getName()).list();
  }

  public void save(T entity) {
    Session session = postgresDatabase.getSession();
    Transaction transaction = postgresDatabase.getTransaction(session);
    session.save(entity);
    transaction.commit();
  }

  protected void setEntityClass(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public void update(T entity) {
    Session session = postgresDatabase.getSession();
    Transaction transaction = postgresDatabase.getTransaction(session);
    session.update(entity);;
    transaction.commit();
  }
}
