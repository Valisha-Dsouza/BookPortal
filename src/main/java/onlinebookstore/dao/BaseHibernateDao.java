package onlinebookstore.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BaseHibernateDao {
    Configuration configuration;
    SessionFactory sessionFactory;

    BaseHibernateDao() {
        configuration = new Configuration();
        sessionFactory = configuration.configure("/hibernate.cfg.xml").buildSessionFactory();
    }

    public <T> void insert(T obj) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.clear();
        session.close();
    }

    public <S> S findById(String id, Class clazz) {
        Session session = getSession();
        S obj = (S) session.load(clazz, id);
        return obj;
    }

    public <T> List<T> getList(Class clazz) {
        Session session = getSession();
        session.createCriteria(clazz).list();
        /*
         * Transaction transaction = session.beginTransaction();
         * transaction.begin(); transaction.commit();
         */
        List<T> resultList = session.createCriteria(clazz).list();
        session.clear();
        session.close();
        return resultList;
    }

    public Session getSession() {
        return this.sessionFactory.openSession();
    }

    public <T> void delete(T id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.delete(id);
        transaction.commit();
        session.clear();
        session.close();
    }

    public <T> List<T> executeQuery(final String queryString) {
        List<T> results = null;

        Query query = getSession().createQuery(queryString);
        results = query.list();
        getSession().clear();
        return results;
    }
}

