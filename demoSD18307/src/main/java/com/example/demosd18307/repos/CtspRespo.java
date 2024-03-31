package com.example.demosd18307.repos;

import com.example.demosd18307.moudel.ChitietSp;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CtspRespo {
    Session sess;
    public ArrayList<ChitietSp> getlist(){
        ArrayList<ChitietSp> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<ChitietSp>) sess.createQuery("FROM ChitietSp ").list();
        sess.close();
        return lst;
    }
    public void add(ChitietSp ctsp){
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.saveOrUpdate(ctsp);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }

    public ChitietSp getdetail(Integer id) {
        Session sess = HibernateUtils.getFACTORY().openSession();
        ChitietSp ct = (ChitietSp) sess.createQuery("from ChitietSp where id =:id_1")
                .setParameter("id_1",id).getSingleResult();
        sess.close();
        return ct;
    }

    public void delete(ChitietSp ctsp) {
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.delete(ctsp);
            tr.commit();
            sess.close();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }
    public void update(ChitietSp ctsp){
        Session sess = null;
        Transaction tr = null;
        try {
            sess = HibernateUtils.getFACTORY().openSession();
            tr = sess.beginTransaction();

            sess.saveOrUpdate(ctsp); // Sử dụng saveOrUpdate để thêm mới hoặc cập nhật đối tượng

            tr.commit();
        } catch (Exception e){
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
//    public List<ChitietSp> findById(int idSp){
//        ArrayList<ChitietSp> rs = new ArrayList<>();
//
//    }
}
