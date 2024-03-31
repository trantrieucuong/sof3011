package com.example.webbanhang.repo;


import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.SanPham;
import org.hibernate.Session;

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
}
