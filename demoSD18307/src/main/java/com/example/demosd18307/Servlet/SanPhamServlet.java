package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.DanhMuc;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.repos.DanhMucRepos;
import com.example.demosd18307.repos.SanPhamRepos;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SanPhamServlet", value = {
        "/sanpham/index",
        "/sanpham/create",
        "/sanpham/store",
        "/sanpham/edit",
        "/sanpham/update",
        "/sanpham/delete"})
public class SanPhamServlet extends HttpServlet {
//    private SanPham sp = new SanPham();
    private SanPhamRepos spres = new SanPhamRepos();
    private DanhMucRepos repo = new DanhMucRepos();
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
        List<SanPham> sp = spres.getlist();
        request.setAttribute("sp",sp);
        request.getRequestDispatcher("/view/san_pham/index.jsp").forward(request,response);
        
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        SanPham sp = spres.getdetail(id);

        spres.delete(sp);

        response.sendRedirect("/sanpham/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        SanPham sanPham = spres.getdetail(id);
        ArrayList<DanhMuc> dm = repo.getlist();
        request.setAttribute("dm",dm);
        request.setAttribute("s",sanPham);
        request.getRequestDispatcher("/view/san_pham/edit.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhMuc> dm = repo.getlist();
        request.setAttribute("dm",dm);
        request.getRequestDispatcher("/view/san_pham/create.jsp").forward(request,response);
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

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        Integer id_dm = Integer.parseInt(request.getParameter("dm"));
        SanPham sp = new SanPham();
        sp.setMaSanpham(id);
        sp.setTenSanpham(ten);
        sp.setTrangThai(trangThai);
        sp.setNgaySua(new Date());
        sp.setNgayTao(new Date());
        DanhMuc dm = new DanhMuc();
        dm.setId(id_dm);
        sp.setDm(dm);
        spres.add(sp);
        response.sendRedirect("/sanpham/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String maSp = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        DanhMuc dm = new DanhMuc();
        Integer id_dm = Integer.parseInt(request.getParameter("dm"));
        SanPham sp = new SanPham();
        sp.setId(id);
        sp.setMaSanpham(maSp);
        sp.setTenSanpham(ten);
        sp.setTrangThai(trangThai);
        sp.setNgaySua(new Date());
        sp.setNgayTao(spres.getdetail(id).getNgayTao());
        dm.setId(id_dm);
        sp.setDm(dm);
        spres.update(sp);
        response.sendRedirect("/sanpham/index");
    }
}
