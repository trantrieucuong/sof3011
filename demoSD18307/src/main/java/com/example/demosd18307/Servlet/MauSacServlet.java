package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.MauSac;
import com.example.demosd18307.repos.MauSacRepos;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MauSacServlet", value = {
        "/mausac/index",
        "/mausac/create",
        "/mausac/store",
        "/mausac/edit",
        "/mausac/update",
        "/mausac/delete"})
public class MauSacServlet extends HttpServlet {
    private MauSacRepos res = new MauSacRepos();
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
        List<MauSac> mau = res.getlist();
        request.setAttribute("ms",mau);
        request.getRequestDispatcher("/view/mau_sac/index.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        MauSac ms = res.getdetail(id);
        res.delete(ms);
        response.sendRedirect("/mausac/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        MauSac ms = res.getdetail(id);
        request.setAttribute("ms",ms);
        request.getRequestDispatcher("/view/mau_sac/edit.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/mau_sac/create.jsp").forward(request,response);
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
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        MauSac ms = new MauSac();
        ms.setId(id);
        ms.setMaMau(ma);
        ms.setTenMau(ten);
        ms.setTrangThai(trangThai);
        ms.setNgaySua(new Date());
        ms.setNgayTao(res.getdetail(id).getNgayTao());
        res.add(ms);
        response.sendRedirect("/mausac/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String trangThai = request.getParameter("trangThai");
        MauSac ms = new MauSac();
        ms.setMaMau(ma);
        ms.setTenMau(ten);
        ms.setTrangThai(trangThai);
        ms.setNgaySua(new Date());
        ms.setNgayTao(new Date());
        res.add(ms);
        response.sendRedirect("/mausac/index");
    }
}
