package com.example.demosd18307.Servlet;

import com.example.demosd18307.moudel.ChitietSp;
import com.example.demosd18307.moudel.MauSac;
import com.example.demosd18307.moudel.SanPham;
import com.example.demosd18307.moudel.Size;
import com.example.demosd18307.repos.CtspRespo;
import com.example.demosd18307.repos.MauSacRepos;
import com.example.demosd18307.repos.SanPhamRepos;
import com.example.demosd18307.repos.SizeRespon;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CtspServlet", value = {
        "/ctsp/index",
        "/ctsp/create",
        "/ctsp/store",
        "/ctsp/edit",
        "/ctsp/update",
        "/ctsp/delete"
})
public class CtspServlet extends HttpServlet {
    private CtspRespo res = new CtspRespo();
    private SanPhamRepos spRes = new SanPhamRepos();
    private MauSacRepos msRes = new MauSacRepos();
    private SizeRespon szRes = new SizeRespon();
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
        List<ChitietSp> ls =res.getlist();
        request.setAttribute("ct",ls);
        request.getRequestDispatcher("/view/ctsp/index.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        ChitietSp ct = res.getdetail(id);

        res.delete(ct);
        response.sendRedirect("/ctsp/index");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        ChitietSp ct = res.getdetail(id);
        List<SanPham> lstSp = spRes.getlist();
        List<MauSac> lstMs = msRes.getlist();
        List<Size> lstSz = szRes.getlist();
        request.setAttribute("sp",lstSp);
        request.setAttribute("ms",lstMs);
        request.setAttribute("sz",lstSz);
        request.setAttribute("data", ct);
        request.getRequestDispatcher("/view/ctsp/edit.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPham> lstSp = spRes.getlist();
        List<MauSac> lstMs = msRes.getlist();
        List<Size> lstSz = szRes.getlist();
        request.setAttribute("sp",lstSp);
        request.setAttribute("ms",lstMs);
        request.setAttribute("sz",lstSz);
        request.getRequestDispatcher("/view/ctsp/create.jsp").forward(request,response);
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
        String ma = request.getParameter("ma");
        Float gia = Float.parseFloat(request.getParameter("gia"));
        Integer slg = Integer.parseInt(request.getParameter("slg"));
        String trangThai = request.getParameter("trangThai");
        Integer id_sp = Integer.parseInt(request.getParameter("sp"));
        Integer id_ms = Integer.parseInt(request.getParameter("ms"));
        Integer id_sz = Integer.parseInt(request.getParameter("sz"));
        SanPham sp = new SanPham();
        MauSac ms = new MauSac();
        Size sz = new Size();
        ChitietSp ct = new ChitietSp();
        sp.setId(id_sp);
        ct.setSp(sp);
        ms.setId(id_ms);
        ct.setMs(ms);
        sz.setId(id_sz);
        ct.setSz(sz);
        ct.setGiaBan(gia);
        ct.setSoLg(slg);
        ct.setTrangThai(trangThai);
        ct.setNgaySua(new Date());
        ct.setNgayTao(new Date());

        res.add(ct);
        response.sendRedirect("/ctsp/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ma = request.getParameter("ma");
        Float gia = Float.parseFloat(request.getParameter("gia"));
        Integer slg = Integer.parseInt(request.getParameter("slg"));
        String trangThai = request.getParameter("trangThai");
        Integer id_sp = Integer.parseInt(request.getParameter("sp"));
        Integer id_ms = Integer.parseInt(request.getParameter("ms"));
        Integer id_sz = Integer.parseInt(request.getParameter("sz"));
        SanPham sp = new SanPham();
        MauSac ms = new MauSac();
        Size sz = new Size();
        ChitietSp ct = new ChitietSp();
        sp.setId(id_sp);
        ct.setSp(sp);
        ms.setId(id_ms);
        ct.setMs(ms);
        sz.setId(id_sz);
        ct.setSz(sz);
        ct.setGiaBan(gia);
        ct.setSoLg(slg);
        ct.setTrangThai(trangThai);
        ct.setNgaySua(new Date());
        ct.setNgayTao(new Date());

        res.add(ct);
        response.sendRedirect("/ctsp/index");

    }
}
