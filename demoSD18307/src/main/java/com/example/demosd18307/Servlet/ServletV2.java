package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.SinhVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletV2", value ={ "/trang-chu","/addSv","/detail","/update","/del"})
public class ServletV2 extends HttpServlet {
    ArrayList<SinhVien> lst ;
    ArrayList<String> lop;

    public ServletV2() {
       lst = new ArrayList<>();
        SinhVien sv = new SinhVien("Thanh",20,"Nam","PH32846","SD18307");
        lst.add(sv);
        lst.add(new SinhVien("Thanh",20,"Nam","PH32346","SD18307"));

        lop = new ArrayList<>();
        lop.add("SD1234");
        lop.add("SD1235");
        lop.add("SD1236");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url = request.getRequestURI();
      if(url.contains("/trang-chu")) {
          request.setAttribute("lstlop", lop);
          request.setAttribute("listSv", lst);
          request.getRequestDispatcher("/Sinhvien/Sinhvien.jsp").forward(request, response);
      }else if(url.contains("/detail")){
          String id = request.getParameter("id");
          SinhVien sinhvien = new SinhVien();
          for(SinhVien s : lst){
              if(s.getId().equals(id)){
                  sinhvien = s;
              }
          }
          request.setAttribute("sv",sinhvien);
          request.setAttribute("lstlop", lop);
          request.getRequestDispatcher("/Sinhvien/AddSV.jsp").forward(request, response);
      }else if(url.contains("/del")){
          String id = request.getParameter("id");
          for(SinhVien sv2 : lst){
              if(sv2.getId().equals(id)){
                  lst.remove(sv2);
                  break;
              }
          }
          response.sendRedirect("/trang-chu");
      }
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/Sinhvien/AddSV.jsp").forward(request,response);
        String uli = request.getRequestURI();
        if(uli.contains("/addSv")){
        String id = request.getParameter("id");
        String ten = request.getParameter("name");
        Integer tuoi = Integer.parseInt(request.getParameter("age"));
        String gioiTinh = request.getParameter("sex");
        System.out.println(gioiTinh);
        String lop = request.getParameter("class");
        SinhVien sv2 = new SinhVien(ten,tuoi,gioiTinh,id,lop);
        System.out.println(sv2.toString());
        lst.add(sv2);

        response.sendRedirect("/trang-chu");}
        else if(uli.contains("/update")){
            String id = request.getParameter("id");
            String ten = request.getParameter("name");
            Integer tuoi = Integer.parseInt(request.getParameter("age"));
            String gioiTinh = request.getParameter("sex");
            System.out.println(gioiTinh);
            String lop = request.getParameter("class");
            SinhVien sv2 = new SinhVien(ten,tuoi,gioiTinh,id,lop);
            System.out.println(sv2.toString());
            for(SinhVien sv :lst){
                if(id.equals(sv.getId())){
                    sv.setTen(ten);
                    sv.setTuoi(tuoi);
                    sv.setSex(gioiTinh);
                    sv.setLop(lop);
                }
            }
            response.sendRedirect("/trang-chu");
        }
    }
}
