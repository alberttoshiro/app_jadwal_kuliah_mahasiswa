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

  protected PostgresDatabase postgresDatabase;
  protected Class<T> entityClass;

  public BaseDAO(PostgresDatabase postgresDatabase) {
    super();
    this.postgresDatabase = postgresDatabase;
  }

  public Query<T> createQuery(String stringQuery, Session session) {
    return session.createQuery(stringQuery, entityClass);
  }

  public void delete(T entity) {
    Session session = postgresDatabase.getSession();
    Transaction transaction = postgresDatabase.getTransaction(session);
    session.delete(entity);
    transaction.commit();
    session.close();
  }

  @Transactional
  public T findById(UUID id) {
    Session session = postgresDatabase.getSession();
    T t = session.get(entityClass, id);
    session.close();
    return t;
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    Session session = postgresDatabase.getSession();
    List<T> list = session.createQuery("from " + entityClass.getName()).list();
    session.close();
    return list;
  }

  @Transactional
  public void save(T entity) {
    Session session = postgresDatabase.getSession();
    Transaction transaction = postgresDatabase.getTransaction(session);
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
    Session session = postgresDatabase.getSession();
    Transaction transaction = postgresDatabase.getTransaction(session);
    session.update(entity);;
    transaction.commit();
    session.close();
  }
}
