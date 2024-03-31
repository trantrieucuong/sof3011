package com.example.webbanhang.sevlet;

import com.example.webbanhang.model.*;
import com.example.webbanhang.repo.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.webbanhang.model.KhachHang;
import com.example.webbanhang.repo.KhachHangRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "XServlet", value = {"/home","/home/add","/home/thanhtoan","/home/hoadonct","/home/delete","/home/update","/home/updateQuantity","/home/addhdct"})
public class XServlet extends HttpServlet {
    private CtspRespo res = new CtspRespo();
    private SizeRespon szres=new SizeRespon();
    private SanPhamRepos spres=new SanPhamRepos();
    private MauSacRepos msses=new MauSacRepos();
    private HoaDonRepo hdrepo=new HoaDonRepo();
    private hdctRePo hdctRepos=new hdctRePo();
    private KhachHangRepo khachHangRepo=new KhachHangRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=request.getRequestURI();
    if(uri.contains("/home")){
        this.index(request, response);
    }else if(uri.contains("/home/delete")){
        Integer id=Integer.parseInt(request.getParameter("id"));
        HoaDonCT hoaDonCT=hdctRepos.getDetail(id);
        hdctRepos.delete(hoaDonCT);
        response.sendRedirect("/home");
    }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ChitietSp> ls =res.getlist();
        List<Size>lsize=szres.getlist();
        List<SanPham>lsanpham=spres.getlist();
        List<MauSac>lms=msses.getlist();
        List<HoaDon>hd=hdrepo.getlist();
        List<HoaDonCT>hdct=hdctRepos.getlist();
        List<KhachHang>kh=khachHangRepo.getlist();


        request.setAttribute("hdct",hdct);
        request.setAttribute("ls",ls);
        request.setAttribute("lsize",lsize);
        request.setAttribute("lsanpham",lsanpham);
        request.setAttribute("lms",lms);
        request.setAttribute("hd",hd);
        request.setAttribute("kh",kh);

        request.getRequestDispatcher("/banHang/x.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=request.getRequestURI();
        if(uri.contains("/home/add")){
            this.add(request, response);
        }else if(uri.contains("/home/thanhtoan")){
            int idHoaDon = Integer.parseInt(request.getParameter("idHoaDon"));

            // Thực hiện cập nhật trạng thái của hóa đơn trong cơ sở dữ liệu
            HoaDonRepo hoaDonRepo = new HoaDonRepo();
            if (hoaDonRepo.updateTrangThaiHoaDon(idHoaDon, "dathanhtoan")) {
                // Trạng thái đã được cập nhật thành công
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect("/home");
            } else {
                // Có lỗi xảy ra khi cập nhật trạng thái
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else if(uri.contains("/home/hoadonct")){
            List<ChitietSp> ls =res.getlist();
            List<Size>lsize=szres.getlist();
            List<SanPham>lsanpham=spres.getlist();
            List<MauSac>lms=msses.getlist();
            List<HoaDon>hd=hdrepo.getlist();

            List<KhachHang>kh=khachHangRepo.getlist();



            request.setAttribute("ls",ls);
            request.setAttribute("lsize",lsize);
            request.setAttribute("lsanpham",lsanpham);
            request.setAttribute("lms",lms);
            request.setAttribute("hd",hd);
            request.setAttribute("kh",kh);
            // Xử lý hiển thị chi tiết hóa đơn
            String idHoaDon = request.getParameter("idHoaDon");
            // Lấy danh sách chi tiết hóa đơn dựa trên ID hóa đơn được chọn
            List<HoaDonCT> hdct = hdctRepos.getListByHoaDonId(Integer.parseInt(idHoaDon));
            // Chuyển hướng người dùng đến trang hiển thị chi tiết hóa đơn
            request.setAttribute("hdct", hdct);
            request.getRequestDispatcher("/banHang/x.jsp").forward(request, response);

        }

        else if (uri.contains("/home/updateQuantity")) {
            this.updateQuantity(request, response);
        }else if(uri.contains("/home/addhdct")){
            this.addhdct(request,response);
        }
    }

    private void addhdct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Tạo một đối tượng HoaDonCT mới
        HoaDonCT hdct = new HoaDonCT();
        hdct.setChitietSp(res.getDetail(productId)); // Lấy thông tin chi tiết sản phẩm dựa trên ID sản phẩm
        hdct.setSoLuongMua(quantity);

        // Thêm đối tượng HoaDonCT vào cơ sở dữ liệu
        hdctRepos.addcthd(hdct);

        // Gửi phản hồi OK về client
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void updateQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy thông tin từ request
        int hdctId = Integer.parseInt(request.getParameter("hdctId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Gọi phương thức để cập nhật số lượng sản phẩm
        hdctRePo hdctRepo = new hdctRePo();
        HoaDonCT hdct = hdctRepo.getDetail(hdctId);
        if (hdct != null) {
            hdct.setSoLuongMua(quantity);
            hdct.setTongTien(quantity * hdct.getGiaBan()); // Cập nhật lại tổng tiền

            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            hdctRepo.update(hdct);
        }
        response.sendRedirect(request.getContextPath() + "/home");
    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id_khach_hang = Integer.parseInt(request.getParameter("id_khach_hang"));
//        String trang_thai = request.getParameter("trang_thai");

        String so_dien_thoai = request.getParameter("so_dien_thoai");
        HoaDon hoaDon = new HoaDon();
        KhachHang khachHang = new KhachHang();
        khachHang.setId(id_khach_hang);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setSoDienThoai(so_dien_thoai);
        hoaDon.setTrangThai("chuathanhtoan");
        hoaDon.setDiaChi("duytan");
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        HoaDonRepo hoaDonRepo = new HoaDonRepo();
        hoaDonRepo.addHoaDon(hoaDon);
        response.sendRedirect("/home");
    }
}
