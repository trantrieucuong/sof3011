package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.DanhMuc;
import com.example.demosd18307.moudel.MauSac;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.moudel.Size;
import com.example.demosd18307.repos.SizeRespon;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SizeServlet", value = {
        "/size/index",
        "/size/create",
        "/size/store",
        "/size/edit",
        "/size/update",
        "/size/delete"})
public class SizeServlet extends HttpServlet {
    SizeRespon sr = new SizeRespon();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Size sz = sr.getdetail(id);

        sr.delete(sz);
        response.sendRedirect("/size/index");

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Size sz = sr.getdetail(id);
        request.setAttribute("sz",sz);
        request.getRequestDispatcher("/view/size/edit.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/size/create.jsp").forward(request,response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Size> sm = this.sr.getlist();
        request.setAttribute("sm",sm);
        request.getRequestDispatcher("/view/size/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            //
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("id");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        Size sz = new Size();
        sz.setId(id);
        sz.setMaSize(ma);
        sz.setTenSize(ten);
        sz.setTrangThai(trangThai);
        sz.setNgayTao(sr.getdetail(id).getNgayTao());
        sz.setNgaySua(new Date());

        sr.update(sz);
        response.sendRedirect("/size/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        Size sz = new Size();
        sz.setMaSize(ma);
        sz.setTenSize(ten);
        sz.setTrangThai(trangThai);
        sz.setNgayTao(new Date());
        sz.setNgaySua(new Date());

        sr.add(sz);
        response.sendRedirect("/size/index");
    }
}
