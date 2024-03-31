package com.example.webbanhang.repo;


import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.MauSac;
import org.hibernate.Session;

import java.util.ArrayList;

public class MauSacRepos {
    Session sess;
    public ArrayList<MauSac> getlist(){
        ArrayList<MauSac> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<MauSac>) sess.createQuery("FROM MauSac ").list();
        sess.close();
        return lst;
    }

}
