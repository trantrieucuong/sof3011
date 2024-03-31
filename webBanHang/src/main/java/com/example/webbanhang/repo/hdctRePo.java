package com.example.webbanhang.repo;

import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.HoaDon;
import com.example.webbanhang.model.HoaDonCT;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import com.example.webbanhang.model.HoaDonCT;
import com.example.webbanhang.connect.HibernateUtils;
import org.hibernate.Session;
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
    public void updateQuantityAndTotalPrice(int hdctId, int newQuantity) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // Lấy chi tiết hóa đơn từ ID
            HoaDonCT hoaDonCT = session.get(HoaDonCT.class, hdctId);
            if (hoaDonCT != null) {
                // Cập nhật số lượng mới
                hoaDonCT.setSoLuongMua(newQuantity);
                // Cập nhật tổng tiền bằng giá mới nhân với số lượng mới
                hoaDonCT.setTongTien(hoaDonCT.getGiaBan() * newQuantity);
                session.update(hoaDonCT);
                transaction.commit();
            } else {
                // Xử lý trường hợp không tìm thấy chi tiết hóa đơn
                System.out.println("Không tìm thấy chi tiết hóa đơn với ID: " + hdctId);
            }
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
