package com.example.demosd18307.repos;

import com.example.demosd18307.moudel.KhachHang;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class KhachHangRepos {
    Session sess;
    public ArrayList<KhachHang> getlist(){
        ArrayList<KhachHang> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<KhachHang>) sess.createQuery("from KhachHang ").list();
        sess.close();
        return lst;
    }
    public void add(KhachHang kh){
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.saveOrUpdate(kh);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }

    public KhachHang getdetail(Integer id) {
        Session sess = HibernateUtils.getFACTORY().openSession();
        KhachHang kh = (KhachHang) sess.createQuery("from KhachHang where id =:id_1")
                .setParameter("id_1",id).getSingleResult();
        sess.close();
        return kh;
    }

    public void delete(KhachHang kh) {
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.delete(kh);
            tr.commit();
            sess.close();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }
    public void update(KhachHang kh){
        Session sess = null;
        Transaction tr = null;
        try {
            sess = HibernateUtils.getFACTORY().openSession();
            tr = sess.beginTransaction();

            sess.saveOrUpdate(kh); // Sử dụng saveOrUpdate để thêm mới hoặc cập nhật đối tượng

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
