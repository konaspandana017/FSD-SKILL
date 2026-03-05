package com.inventory.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class ProductDataLoader {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();

            session.persist(new Product("Notebook", "Spiral notebook", 5.99, 100));
            session.persist(new Product("Pen Set", "Set of colored pens", 12.50, 75));
            session.persist(new Product("Mouse", "Wireless mouse", 25.50, 50));
            session.persist(new Product("Desk Lamp", "LED desk lamp", 35.75, 25));
            session.persist(new Product("Keyboard", "Mechanical keyboard", 45.00, 30));
            session.persist(new Product("Desk Chair", "Ergonomic chair", 150.00, 0));
            session.persist(new Product("Monitor", "24-inch monitor", 299.99, 20));
            session.persist(new Product("Laptop", "Gaming laptop", 999.99, 15));
            session.persist(new Product("Laptop", "Work laptop", 72000.00, 8));

            session.getTransaction().commit();
            System.out.println("Products inserted successfully!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
