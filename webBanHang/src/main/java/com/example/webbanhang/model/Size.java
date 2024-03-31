package com.example.webbanhang.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="size")
@Entity
public class Size {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;
        @Column(name = "ma_size")
        private String maSize;
        @Column(name = "ten_size")
        private String tenSize;
        @Column(name = "trang_thai")
        private String trangThai;
        @Column(name = "ngay_tao")
        private Date ngayTao;
        @Column(name = "ngay_sua")
        private Date ngaySua;
}
