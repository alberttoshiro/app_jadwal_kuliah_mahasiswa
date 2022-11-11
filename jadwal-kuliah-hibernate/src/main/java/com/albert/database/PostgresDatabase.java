package com.albert.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class PostgresDatabase {

  private static SessionFactory sessionFactory;

  private static void buildSessionFactory() {
    StandardServiceRegistry standardServiceRegistry =
        new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
    sessionFactory = metadata.getSessionFactoryBuilder().build();
  }

  public static final Session getSession() {
    if (sessionFactory == null) {
      buildSessionFactory();
    }
    Session session = null;
    try {
      session = sessionFactory.openSession();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return session;
  }

  public static final Transaction getTransaction(Session session) {
    Transaction transaction = session.getTransaction();
    if (!TransactionStatus.ACTIVE.equals(transaction.getStatus())) {
      transaction = session.beginTransaction();
    }
    return transaction;
  }

  public PostgresDatabase() {
    super();
    if (sessionFactory == null) {
      StandardServiceRegistry standardServiceRegistry =
          new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
      Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
      sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
  }
}
