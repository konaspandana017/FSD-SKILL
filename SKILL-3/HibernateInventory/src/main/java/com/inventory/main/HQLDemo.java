package com.inventory.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.inventory.util.HibernateUtil;
import com.inventory.entity.Product;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
        	getProductsWithHighStock(session);
        } finally {
            session.close();
            factory.close();
        }
    }

    public static void updateProductPrice(Session session) {

        session.beginTransaction();

        String hql = "UPDATE Product p SET p.price = p.price + 100 WHERE p.name = :name";
        var query = session.createQuery(hql);
        query.setParameter("name", "Laptop");

        int rows = query.executeUpdate();

        session.getTransaction().commit();

        System.out.println("Rows Updated: " + rows);
    }
    
    public static void getProductsWithHighStock(Session session) {

        String hql = "SELECT p.name, SUM(p.quantity) " +
                     "FROM Product p " +
                     "GROUP BY p.name " +
                     "HAVING SUM(p.quantity) > 20";

        var query = session.createQuery(hql, Object[].class);
        var results = query.list();

        for (Object[] row : results) {
            String name = (String) row[0];
            Long totalQty = (Long) row[1];

            System.out.println(name + " - Total Quantity: " + totalQty);
        }
    }

    public static void getTotalQuantityPerProduct(Session session) {

        String hql = "SELECT p.name, SUM(p.quantity) FROM Product p GROUP BY p.name";
        var query = session.createQuery(hql, Object[].class);

        var results = query.list();

        for (Object[] row : results) {
            String name = (String) row[0];
            Long totalQty = (Long) row[1];

            System.out.println(name + " - Total Quantity: " + totalQty);
        }
    }

    public static void deleteOutOfStockProducts(Session session) {

        session.beginTransaction();

        String hql = "DELETE FROM Product p WHERE p.quantity = 0";
        var query = session.createQuery(hql);

        int rows = query.executeUpdate();

        session.getTransaction().commit();

        System.out.println("Out of stock products deleted: " + rows);
    }


    public static void sortProductsByPriceAscending(Session session) {

        String hql = "FROM Product p ORDER BY p.price ASC";
        var query = session.createQuery(hql, Product.class);
        var products = query.list();

        for (var product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }
    public static void deleteProductById(Session session, int id) {

        session.beginTransaction();

        String hql = "DELETE FROM Product p WHERE p.id = :id";
        var query = session.createQuery(hql);
        query.setParameter("id", id);

        int rows = query.executeUpdate();

        session.getTransaction().commit();

        System.out.println("Rows Deleted: " + rows);
    }


    public static void sortProductsByPriceDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.price DESC";
        var query = session.createQuery(hql, Product.class);
        var products = query.list();

        for (var product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }

    public static void sortProductsByQuantityDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.quantity DESC";
        var query = session.createQuery(hql, Product.class);
        var products = query.list();

        for (var product : products) {
            System.out.println(product.getName() + " - Quantity: " + product.getQuantity());
        }
    }

    public static void getFirstThreeProducts(Session session) {

        String hql = "FROM Product p";
        var query = session.createQuery(hql, Product.class);
        query.setFirstResult(0);
        query.setMaxResults(3);

        var products = query.list();

        for (var product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }

    public static void getNextThreeProducts(Session session) {

        String hql = "FROM Product p";
        var query = session.createQuery(hql, Product.class);
        query.setFirstResult(3);
        query.setMaxResults(3);

        var products = query.list();

        for (var product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }

    public static void countProductsInStock(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0";
        var query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();

        System.out.println("Products In Stock: " + count);
    }

    public static void countTotalProducts(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p";
        var query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total Products: " + count);
    }
}
