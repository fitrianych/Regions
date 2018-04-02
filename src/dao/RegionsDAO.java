/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Employees;
import entities.Regions;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author Asus
 */
public class RegionsDAO implements InterfaceDAO{
    public SessionFactory factory;
    private Session session;
    private Transaction transaction;
    
    public RegionsDAO() {
        this.factory = HibernateUtil
                .getSessionFactory();
    }

    @Override
    public boolean insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * fungsi delete pada regions
     * @param object
     * @return 
     */
    @Override
    public boolean delete(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Regions reg = (Regions) session
                    .get(Regions.class, 
                            Integer.parseInt(object+""));
            session.delete(reg);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction!=null)transaction.rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public List<Object> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> search(String category, String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public Object getById(String id) {
        Object reg = new Object();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            reg = session
                    .createQuery("FROM Regions"
                            + " WHERE regionid="+id+"").uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction!=null)transaction.rollback();
        } finally {
            session.close();
        }
        return reg;
    }
    
}