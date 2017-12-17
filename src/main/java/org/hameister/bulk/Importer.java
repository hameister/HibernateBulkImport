package org.hameister.bulk;

import org.hameister.bulk.data.Item;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by hameister on 03.12.17.
 */
public class Importer {

    public Importer() throws Exception {

        EntityManager entityManager = createEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setId(UUID.randomUUID().toString());
            item.setLocation("Board");
            item.setDescription("Item " + i + "IT:" + date);
            entityManager.persist(item);

            //Check when the SQL-Statements are written to the console
            if (i == 4) {
                entityManager.flush();
                entityManager.clear();
            }
        }


        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Commit");
    }


    protected EntityManagerFactory createEntityManagerFactory() throws Exception {
        return Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

}
