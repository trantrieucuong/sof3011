package com.example.webbanhang.sevlet;

import com.example.webbanhang.model.*;
import com.example.webbanhang.repo.*;
import com.google.gson.Gson;
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

@WebServlet(name = "XServlet", value = {"/home","/home/createInvoice","/home/searchCustomer","/home/thanhtoan","/home/hoadonct","/home/delete","/home/update","/home/updateQuantity","/home/addToCart","/home/add/index"})
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
    if(uri.equals("/home")){
        this.index(request, response);
    }else if(uri.equals("/home/delete")){
        Integer id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id+"===============");
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
        if(uri.equals("/home/createInvoice")){
            this.add(request, response);
        }else if(uri.equals("/home/thanhtoan")){
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
        else if(uri.equals("/home/hoadonct")){
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

        }else if(uri.equals("/home/searchCustomer")){
            String phoneNumber = request.getParameter("phone_number");

            // Gọi phương thức để tìm kiếm khách hàng theo số điện thoại
            KhachHangRepo customerRepo = new KhachHangRepo();
            KhachHang customer = customerRepo.findByPhoneNumber(phoneNumber);

            // Gửi thông tin khách hàng tìm được dưới dạng JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(customer));
        }

        else if (uri.equals("/home/updateQuantity")) {
            this.updateQuantity(request, response);
        }else if(uri.equals("/home/addToCart")){
            this.addhdct(request,response);
        }
    }

    private void addhdct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float giaBan = Float.parseFloat(request.getParameter("giaBan"));

        // Tạo một đối tượng HoaDonCT mới
        HoaDonCT hdct = new HoaDonCT();


        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(productId);
        hdct.setHoaDon(hoaDon);

        // Lấy thông tin chi tiết sản phẩm dựa trên ID sản phẩm
        ChitietSp chitietSp = res.getDetail(productId);
        hdct.setChitietSp(chitietSp);
        hdct.setGiaBan(giaBan);
        hdct.setTrangThai("active");
        hdct.setNgayTao(new Date());
        hdct.setNgaySua(new Date());



        // Đặt số lượng mua
        hdct.setSoLuongMua(quantity);

        // Tính tổng tiền
        double tongTien = giaBan * quantity;
        hdct.setTongTien(tongTien);

        // Thêm đối tượng HoaDonCT vào cơ sở dữ liệu
        hdctRepos.addcthd(hdct);

        // Gửi phản hồi OK về client
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void updateQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy thông tin từ request
        Integer hdctId = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Gọi phương thức để cập nhật số lượng sản phẩm

        HoaDonCT hdct = hdctRepos.getDetail(hdctId);
        if (hdct != null) {
            hdct.setSoLuongMua(quantity);
            hdct.setTongTien(quantity * hdct.getGiaBan()); // Cập nhật lại tổng tiền

            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            hdctRepos.update(hdct);
        }
        response.sendRedirect(request.getContextPath() + "/home");
    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Integer id_khach_hang = Integer.parseInt(request.getParameter("id_khach_hang"));
////        String trang_thai = request.getParameter("trang_thai");
//
//        String so_dien_thoai = request.getParameter("so_dien_thoai");
//        HoaDon hoaDon = new HoaDon();
//        KhachHang khachHang = new KhachHang();
//        khachHang.setId(id_khach_hang);
//        hoaDon.setKhachHang(khachHang);
//        hoaDon.setSoDienThoai(so_dien_thoai);
//        hoaDon.setTrangThai("chuathanhtoan");
//        hoaDon.setDiaChi("duytan");
//        hoaDon.setNgayTao(new Date());
//        hoaDon.setNgaySua(new Date());
//        HoaDonRepo hoaDonRepo = new HoaDonRepo();
//        hoaDonRepo.addHoaDon(hoaDon);
//        response.sendRedirect("/home");
        Integer customerId = Integer.parseInt(request.getParameter("customer_id"));
        String phoneNumber = request.getParameter("phone_number");

        // Tạo đối tượng HoaDon từ thông tin được cung cấp
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayTao(new Date()); // Đặt ngày tạo hoá đơn là ngày hiện tại
        hoaDon.setTrangThai("chuathanhtoan"); // Đặt trạng thái hoá đơn là Active
        hoaDon.setDiaChi(""); // Đặt địa chỉ hoá đơn (cần cung cấp thông tin địa chỉ từ form)
        hoaDon.setSoDienThoai(phoneNumber); // Đặt số điện thoại hoá đơn

        // Lấy thông tin khách hàng từ ID khách hàng
        KhachHangRepo khachHangRepo = new KhachHangRepo();
        KhachHang khachHang = khachHangRepo.findById(customerId);
        hoaDon.setKhachHang(khachHang); // Đặt khách hàng cho hoá đơn

        // Lưu hoá đơn vào cơ sở dữ liệu
        HoaDonRepo hoaDonRepo = new HoaDonRepo();
        hoaDonRepo.addHoaDon(hoaDon);

        // Chuyển hướng trang về trang chủ sau khi tạo hoá đơn thành công
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
