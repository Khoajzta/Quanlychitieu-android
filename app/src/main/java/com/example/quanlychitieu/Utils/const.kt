package com.example.quanlychitieu.Utils

import androidx.compose.ui.graphics.Color
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.domain.model.ThuNhapModel

const val BASE_URL = "http://chillcup.io.vn/QuanLyThuChi/public/"

val thuNhapListSample = listOf(
    ThuNhapModel(
        maThuNhap = 1,
        tenThuNhap = "Lương tháng 1",
        soTien = 15000000,
        maThang = 1,
        ngayThuNhap = "2025-01-05"
    ),
    ThuNhapModel(
        maThuNhap = 2,
        tenThuNhap = "Lương tháng 2",
        soTien = 15500000,
        maThang = 2,
        ngayThuNhap = "2025-02-05"
    ),
    ThuNhapModel(
        maThuNhap = 3,
        tenThuNhap = "Thưởng dự án",
        soTien = 5000000,
        maThang = 2,
        ngayThuNhap = "2025-02-20"
    ),
    ThuNhapModel(
        maThuNhap = 4,
        tenThuNhap = "Lương tháng 3",
        soTien = 16000000,
        maThang = 3,
        ngayThuNhap = "2025-03-05"
    ),
    ThuNhapModel(
        maThuNhap = 5,
        tenThuNhap = "Bán đồ online",
        soTien = 2000000,
        maThang = 3,
        ngayThuNhap = "2025-03-15"
    )
)

object listKhoanChiConst {

    val listTaiKhoan = listOf(
        TaiKhoanModel(
            id = 1,
            id_nguoidung = 1,
            ten_taikhoan = "Tiền mua xe",
            so_du = 2500000,
            loai_taikhoan = 1,
            mo_ta = "Tiền để dành mua xe"
        ),
        TaiKhoanModel(
            id = 1,
            id_nguoidung = 1,
            ten_taikhoan = "Tiền mua xe",
            so_du = 2500000,
            loai_taikhoan = 1,
            mo_ta = "Tiền để dành mua xe"
        ),
        TaiKhoanModel(
            id = 1,
            id_nguoidung = 1,
            ten_taikhoan = "Tiền mua xe",
            so_du = 2500000,
            loai_taikhoan = 1,
            mo_ta = "Tiền để dành mua xe"
        ),
        TaiKhoanModel(
            id = 1,
            id_nguoidung = 1,
            ten_taikhoan = "Tiền mua xe",
            so_du = 2500000,
            loai_taikhoan = 1,
            mo_ta = "Tiền để dành mua xe"
        )
    )
    val listKhoanChi = listOf(
        KhoanChiModel(
            id = 1,
            ten_khoanchi = "Tiền ăn",
            id_nguoidung = 1,
            mausac = "red",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "blue",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "yellow",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "green",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "yellow",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
    )
}
