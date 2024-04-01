package com.example.webbanhang.repo;

import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.KhachHang;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public KhachHang findByPhoneNumber(String phoneNumber) {
        Session session = null;
        try {
            session = HibernateUtils.getFACTORY().openSession();
            String hql = "FROM KhachHang WHERE sdt = :phoneNumber";
            Query<KhachHang> query = session.createQuery(hql);
            query.setParameter("phoneNumber", phoneNumber);
            List<KhachHang> results = query.list();
            if (!results.isEmpty()) {
                return results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
