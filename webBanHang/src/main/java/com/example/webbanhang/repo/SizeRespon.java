package com.example.webbanhang.repo;


import com.example.webbanhang.connect.HibernateUtils;
import com.example.webbanhang.model.Size;
import org.hibernate.Session;

import java.util.ArrayList;

public class SizeRespon {
    Session sess;
    public ArrayList<Size> getlist(){
        ArrayList<Size> lst = new ArrayList<>();
        sess = HibernateUtils.getFACTORY().openSession();
        lst = (ArrayList<Size>) sess.createQuery("FROM Size ").list();
        sess.close();
        return lst;
    }
}
