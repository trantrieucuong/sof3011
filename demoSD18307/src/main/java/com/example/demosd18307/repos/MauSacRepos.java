package com.example.demosd18307.repos;

import com.example.demosd18307.moudel.MauSac;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MauSacRepos {
    Session sess;
    public ArrayList<MauSac> getlist(){
        ArrayList<MauSac> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<MauSac>) sess.createQuery("FROM MauSac ").list();
        sess.close();
        return lst;
    }
    public void add(MauSac ms){
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.saveOrUpdate(ms);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }
    public MauSac getdetail(Integer id) {
        Session sess = HibernateUtils.getFACTORY().openSession();
        MauSac ms = (MauSac) sess.createQuery("from MauSac where id =:id_1")
                .setParameter("id_1",id).getSingleResult();
        sess.close();
        return ms;
    }

    public void delete(MauSac ms) {
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.delete(ms);
            tr.commit();
            sess.close();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }
    public void update(MauSac ms){
        Session sess = null;
        Transaction tr = null;
        try {
            sess = HibernateUtils.getFACTORY().openSession();
            tr = sess.beginTransaction();

            sess.saveOrUpdate(ms); // Sử dụng saveOrUpdate để thêm mới hoặc cập nhật đối tượng

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

}
