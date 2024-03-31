package com.example.demosd18307.repos;

import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.moudel.Size;
import com.example.demosd18307.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SizeRespon {
    Session sess;
    public ArrayList<Size> getlist(){
        ArrayList<Size> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<Size>) sess.createQuery("FROM Size ").list();
        sess.close();
        return lst;
    }

    public void add(Size sz){
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.saveOrUpdate(sz);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }

    public Size getdetail(Integer id) {
        Session sess = HibernateUtils.getFACTORY().openSession();
        Size sz = (Size) sess.createQuery("from Size where id =:id_1")
                .setParameter("id_1",id).getSingleResult();
        sess.close();
        return sz;
    }

    public void delete(Size sz) {
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.delete(sz);
            tr.commit();
            sess.close();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }
    public void update(Size sz){
        Session sess = null;
        Transaction tr = null;
        try {
            sess = HibernateUtils.getFACTORY().openSession();
            tr = sess.beginTransaction();

            sess.saveOrUpdate(sz); // Sử dụng saveOrUpdate để thêm mới hoặc cập nhật đối tượng

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
