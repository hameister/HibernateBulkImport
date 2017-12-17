package org.hameister.bulk;

import org.hameister.bulk.data.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by hameister on 03.12.17.
 */
public class Importer {
    private EntityManagerFactory sessionFactory;


    public Importer() throws Exception {
        setUp();
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

            String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setId(UUID.randomUUID().toString());
            item.setLocation("Board");
            item.setDescription("Item " + i + "IT:" + date);
            entityManager.persist(item);

            //Check when the SQL-Statements are written to the console
            if (i==4) {
                entityManager.flush();
                entityManager.clear();
            }
        }


        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Commit");
    }


    protected void setUp() throws Exception {
        sessionFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
    }

    public void startImport() {
        System.out.println("Importing....");
    }
}
