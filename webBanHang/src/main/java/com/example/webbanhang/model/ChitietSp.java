package com.example.webbanhang.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ctsp")
@Entity
public class ChitietSp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "gia_ban")
    private Float giaBan;
    @Column(name = "so_luong_ton")
    private Integer soLg;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;


    @ManyToOne
    @JoinColumn(name = "id_sp")
    private SanPham sp;
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac ms;
    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size sz;

}
