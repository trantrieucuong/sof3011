package com.example.webbanhang.repo;

import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.ChitietSp;
import com.example.webbanhang.model.HoaDon;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class HoaDonRepo {
    Session sess;
    public ArrayList<HoaDon> getlist(){
        ArrayList<HoaDon> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<HoaDon>) sess.createQuery("FROM HoaDon ").list();
        sess.close();
        return lst;
    }
    public void addHoaDon(HoaDon hoaDon) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(hoaDon);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public HoaDon getdetail(Integer id) {
        Session sess = HibernateUtils.getFACTORY().openSession();
        HoaDon hd = (HoaDon) sess.createQuery("from HoaDon where id =:id_1")
                .setParameter("id_1",id).getSingleResult();
        sess.close();
        return hd;
    }
    public void delete(HoaDon hd) {
        sess = HibernateUtils.getFACTORY().openSession();
        Transaction tr = sess.beginTransaction();
        try {
            sess.delete(hd);
            tr.commit();
            sess.close();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        sess.close();
    }

    public HoaDon getHoaDonById(int id) {
        Session session = null;
        HoaDon hoaDon = null;
        try {
            session = HibernateUtils.getFACTORY().openSession();
            hoaDon = session.get(HoaDon.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return hoaDon;
    }
    public boolean updateTrangThaiHoaDon(int idHoaDon, String newTrangThai) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getFACTORY().openSession();
            transaction = session.beginTransaction();

            // Tìm hóa đơn cần cập nhật
            HoaDon hoaDon = session.get(HoaDon.class, idHoaDon);

            if (hoaDon != null) {
                // Cập nhật trạng thái của hóa đơn
                hoaDon.setTrangThai(newTrangThai);
                session.update(hoaDon);

                // Commit transaction
                transaction.commit();
                return true;
            } else {
                // Hóa đơn không tồn tại
                return false;
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
