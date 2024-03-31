package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.DanhMuc;
import com.example.demosd18307.repos.DanhMucRepos;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DanhMucServlet", value = {
        "/danhmuc/index",
        "/danhmuc/create",
        "/danhmuc/store",
        "/danhmuc/edit",
        "/danhmuc/update",
        "/danhmuc/delete"})
public class DanhMucServlet extends HttpServlet {

    DanhMucRepos repos = new DanhMucRepos();
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
        DanhMuc dm = repos.getdetail(id);
        repos.delete(dm);

        response.sendRedirect("/danhmuc/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DanhMuc dm = repos.getdetail(id);
        request.setAttribute("dm",dm);
        request.getRequestDispatcher("/view/danh_muc/create.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/danh_muc/create.jsp").forward(request,response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhMuc> dm = this.repos.getlist();
        request.setAttribute("dm",dm);
        request.getRequestDispatcher("/view/danh_muc/index.jsp").forward(request,response);
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
        String id = request.getParameter("id");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(id);
        dm.setTenDanhMuc(ten);
        dm.setTrangThai(trangThai);
        dm.setNgaySua(new Date());
        dm.setNgayTao(new Date());

        repos.update(dm);
        response.sendRedirect("/danhmuc/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(id);
        dm.setTenDanhMuc(ten);
        dm.setTrangThai(trangThai);
        dm.setNgaySua(new Date());
        dm.setNgayTao(new Date());

        repos.add(dm);
        response.sendRedirect("/danhmuc/index");
    }

}
