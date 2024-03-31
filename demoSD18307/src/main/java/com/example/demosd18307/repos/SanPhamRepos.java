package com.example.demosd18307.repos;

import com.example.demosd18307.moudel.DanhMuc;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SanPhamRepos {
    Session sess;
    public ArrayList<SanPham> getlist(){
        ArrayList<SanPham> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<SanPham>) sess.createQuery(" FROM SanPham ").list();
        sess.close();
        return lst;
    }
    public void add(SanPham sp){
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.saveOrUpdate(sp);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }

    public SanPham getdetail(Integer id) {
        Session sess = HibernateUtils.getFACTORY().openSession();
        SanPham sp = (SanPham) sess.createQuery("from SanPham where id =:id_1")
                .setParameter("id_1",id).getSingleResult();
        sess.close();
        return sp;
    }

    public void delete(SanPham sp) {
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.delete(sp);
            tr.commit();
            sess.close();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }
    public void update(SanPham sp){
        Session sess = null;
        Transaction tr = null;
        try {
            sess = HibernateUtils.getFACTORY().openSession();
            tr = sess.beginTransaction();

            sess.saveOrUpdate(sp); // Sử dụng saveOrUpdate để thêm mới hoặc cập nhật đối tượng

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
