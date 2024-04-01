package com.example.webbanhang.repo;


import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.ChitietSp;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CtspRespo {
    Session sess;



    public ArrayList<ChitietSp> getlist(){
        ArrayList<ChitietSp> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<ChitietSp>) sess.createQuery("FROM ChitietSp ").list();
        sess.close();
        return lst;
    }
    public void add(ChitietSp chitietSp){
        Transaction transaction=null;
        sess=HibernateUtils.getFACTORY().openSession();
        transaction=sess.beginTransaction();
        try{

            sess.saveOrUpdate(chitietSp);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            sess.close();
        }
    }
    public static ChitietSp getDetail(Integer id){
        Session session=HibernateUtils.getFACTORY().openSession();
        ChitietSp chitietSp= (ChitietSp) session.createQuery("from ChitietSp where id= :id_1").setParameter("id_1",id).getSingleResult();
        session.close();
        return chitietSp;

    }
    public void delete(ChitietSp ctsp){
        Session session=HibernateUtils.getFACTORY().openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.delete(ctsp);
            transaction.commit();
            session.close();

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
    }
    public void update(ChitietSp ctsp) {
        Session sess = null;
        Transaction tr = null;
        try {
            sess = HibernateUtils.getFACTORY().openSession();
            tr = sess.beginTransaction();

            sess.saveOrUpdate(ctsp); // Sử dụng saveOrUpdate để thêm mới hoặc cập nhật đối tượng

            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            if (sess != null && sess.isOpen()) {
                sess.close();
            }
        }
    }
}
