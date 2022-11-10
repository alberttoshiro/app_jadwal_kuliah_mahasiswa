package com.albert.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostgresDatabase {

  private StandardServiceRegistry standardServiceRegistry;
  private Metadata metadata;
  private SessionFactory sessionFactory;

  public PostgresDatabase() {
    super();
    standardServiceRegistry =
        new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
    sessionFactory = metadata.getSessionFactoryBuilder().build();
  }

  public final Session getSession() {
    Session session = null;
    try {
      session = this.sessionFactory.openSession();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return session;
  }

  public final Transaction getTransaction(Session session) {
    Transaction transaction = session.getTransaction();
    if (!TransactionStatus.ACTIVE.equals(transaction.getStatus())) {
      transaction = session.beginTransaction();
    }
    return transaction;
  }
}
