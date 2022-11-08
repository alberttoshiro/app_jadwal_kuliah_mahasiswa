package com.albert.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PostgresDatabase {

  private StandardServiceRegistry standardServiceRegistry;
  private Metadata metadata;
  private SessionFactory sessionFactory;
  private Session session;
  private Transaction transaction;

  public PostgresDatabase() {
    super();
    standardServiceRegistry =
        new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
    sessionFactory = metadata.getSessionFactoryBuilder().build();
    session = sessionFactory.openSession();
    transaction = session.beginTransaction();
  }


}
