package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.KhachHang;
import com.example.demosd18307.repos.KhachHangRepos;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "KhachHangServlet", value = {
        "/khachhang/index",
        "/khachhang/create",
        "/khachhang/store",
        "/khachhang/edit",
        "/khachhang/update",
        "/khachhang/delete"
})
public class KhachHangServlet extends HttpServlet {
    private KhachHangRepos rs = new KhachHangRepos();
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

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<KhachHang> kh = rs.getlist();
        request.setAttribute("kh",kh);
        request.getRequestDispatcher("/view/khach_hang/index.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        KhachHang kh = rs.getdetail(id);

        rs.delete(kh);
        response.sendRedirect("/khachhang/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        KhachHang kh = rs.getdetail(id);
        request.setAttribute("kh",kh);
        request.getRequestDispatcher("/view/khach_hang/edit.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/khach_hang/create.jsp").forward(request,response);
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
        String ten = request.getParameter("ten");
        String diachi = request.getParameter("diaChi");
        String sdt = request.getParameter("sdt");
        String trangthai = request.getParameter("trangThai");
        KhachHang kh = new KhachHang();
        kh.setId(id);
        kh.setHoTen(ten);
        kh.setDiaChi(diachi);
        kh.setSdt(sdt);
        kh.setTrangThai(trangthai);
        kh.setNgaySua(new Date());
        kh.setNgayTao(rs.getdetail(id).getNgayTao());

        rs.add(kh);
        response.sendRedirect("/khachhang/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        String diachi = request.getParameter("diaChi");
        String sdt = request.getParameter("sdt");
        String trangthai = request.getParameter("trangThai");
        KhachHang kh = new KhachHang();
        kh.setHoTen(ten);
        kh.setDiaChi(diachi);
        kh.setSdt(sdt);
        kh.setTrangThai(trangthai);
        kh.setNgaySua(new Date());
        kh.setNgayTao(new Date());

        rs.add(kh);
        response.sendRedirect("/khachhang/index");
    }
}
