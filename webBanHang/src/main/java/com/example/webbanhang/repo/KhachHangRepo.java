package com.example.webbanhang.repo;

import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.KhachHang;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class KhachHangRepo {
    Session sess;

    public ArrayList<KhachHang> getlist() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<KhachHang>) sess.createQuery("FROM KhachHang").list();
        sess.close();
        return lst;
    }

    public KhachHang findById(Integer id) {
        Session session = null;
        try {
            session = HibernateUtils.getFACTORY().openSession();
            return session.get(KhachHang.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<KhachHang> findByPhoneNumber(String phoneNumber) {
        Session session = null;
        try {
            session = HibernateUtils.getFACTORY().openSession();
            // Sử dụng câu truy vấn HQL để tìm kiếm khách hàng với số điện thoại cụ thể
            return session.createQuery("FROM KhachHang WHERE sdt = :phoneNumber", KhachHang.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getResultList();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
