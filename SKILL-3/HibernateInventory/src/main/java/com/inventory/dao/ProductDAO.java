package com.inventory.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class ProductDAO {

    // CREATE / INSERT a product
    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        session.close();
    }

    // READ / Retrieve a product by id
    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    // UPDATE price and quantity
    public void updateProduct(int id, double price, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Product product = session.get(Product.class, id);
        if(product != null) {
            product.setPrice(price);
            product.setQuantity(quantity);
            session.update(product);
        }
        tx.commit();
        session.close();
    }

    // DELETE a product by id
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Product product = session.get(Product.class, id);
        if(product != null) {
            session.delete(product);
        }
        tx.commit();
        session.close();
    }
}
