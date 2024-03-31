package com.example.webbanhang.repo;

import com.example.webbanhang.connect.HibernateUtils;

import com.example.webbanhang.model.HoaDonCT;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class hdctRePo {
    Session sess;
    public ArrayList<HoaDonCT> getlist(){
        ArrayList<HoaDonCT> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<HoaDonCT>) sess.createQuery("FROM HoaDonCT ").list();
        sess.close();
        return lst;
    }
    public List<HoaDonCT> getListByHoaDonId(int hoaDonId) {
        Session session = HibernateUtils.getFACTORY().openSession();
        List<HoaDonCT> hdctList = null;
        try {
            // Sử dụng HQL để lấy danh sách chi tiết hóa đơn dựa trên ID hóa đơn
            String hql = "FROM HoaDonCT WHERE hoaDon.id = :hoaDonId";
            Query<HoaDonCT> query = session.createQuery(hql, HoaDonCT.class);
            query.setParameter("hoaDonId", hoaDonId);
            hdctList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return hdctList;

    }
    public static HoaDonCT getDetail(Integer id){
        Session session=HibernateUtils.getFACTORY().openSession();
        HoaDonCT hoaDonCT= (HoaDonCT) session.createQuery("from HoaDonCT where id= :id_1").setParameter("id_1",id).getSingleResult();
        session.close();
        return hoaDonCT;

    }
    public void delete(HoaDonCT hoaDonCT){
        Session session=HibernateUtils.getFACTORY().openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.delete(hoaDonCT);
            transaction.commit();
            session.close();

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
    }
    public void update(HoaDonCT hdct) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(hdct);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }public void addcthd(HoaDonCT chiTietHoaDon) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(chiTietHoaDon);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
