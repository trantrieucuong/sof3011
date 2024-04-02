package com.example.demosd18307.repos;

import com.example.demosd18307.moudel.DanhMuc;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DanhMucRepos {
    Session sess;
    public ArrayList<DanhMuc> getlist(){
        ArrayList<DanhMuc> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<DanhMuc>) sess.createQuery("FROM DanhMuc ORDER BY ngayTao").list();
        sess.close();
        return lst;
    }

    public void add(DanhMuc dm){
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.saveOrUpdate(dm);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
       sess.close();
    }
    public DanhMuc getdetail(Integer id) {
        Session sess = HibernateUtils.getFACTORY().openSession();
        DanhMuc dm = (DanhMuc) sess.createQuery("from DanhMuc where id =:id_1")
                .setParameter("id_1",id).getSingleResult();
        sess.close();
        return dm;
    }

    public void delete(DanhMuc dm) {
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.delete(dm);
            tr.commit();
            sess.close();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }
    public void update(DanhMuc dm){
        Session sess = null;
        Transaction tr = null;
        try {
            sess = HibernateUtils.getFACTORY().openSession();
            tr = sess.beginTransaction();

            sess.saveOrUpdate(dm); // Sử dụng saveOrUpdate để thêm mới hoặc cập nhật đối tượng

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
    public static void main(String[] args) {
        ArrayList<DanhMuc> list = new DanhMucRepos().getlist();
        for(DanhMuc dm :list){
            System.out.println(dm);
        }
    }
}
