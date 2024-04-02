package com.example.webbanhang.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hdct")
@Entity

public class HoaDonCT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "id_ctsp")
    private ChitietSp chitietSp;


    public Integer getId() {
        return id;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public ChitietSp getChitietSp() {
        return chitietSp;
    }

    public String getTenSanPham() {
        if (chitietSp != null && chitietSp.getSp() != null) {
            return chitietSp.getSp().getTenSanpham();
        }
        return null;
    }

    public Integer getSoLuongMua() {
        return soLuongMua;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    @Column(name = "so_luong_mua")
    private Integer soLuongMua;
    @Column(name = "gia_ban")
    private double giaBan;
    @Column(name = "tong_tien")
    private double tongTien;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;



}
