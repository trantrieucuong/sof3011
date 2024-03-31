package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.HoaDon;
import com.example.demosd18307.repos.HoaDonRespo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HoaDonServlet", value = {
        "/hoadon/index",
        "/hoadon/create",
        "/hoadon/edit",
        "/hoadon/delete",
        "/hoadon/store",
        "/hoadon/update"
})
public class HoaDonServlet extends HttpServlet {
    private HoaDonRespo hdRes = new HoaDonRespo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("create")){
            this.create(request,response);
        } else if (uri.contains("edit")) {
            this.edit(request,response);
        } else if (uri.contains("delete")) {
            this.delete(request,response);
        }else {
            this.index(request,response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
        List<HoaDon> hd = this.hdRes.getList();
        request.setAttribute("hd",hd);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        HoaDon hd = hdRes.getdetail(id);

        hdRes.delete(hd);
        response.sendRedirect("/hoadon/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {

    }

    private void create(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else {
            //
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {

    }

    private void store(HttpServletRequest request, HttpServletResponse response) {

    }
}
