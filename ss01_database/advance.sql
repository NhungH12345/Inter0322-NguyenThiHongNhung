CREATE DATABASE QuanLyKhuNghiDuong;
USE QuanLyKhuNghiDuong;
-- 21.Tạo khung nhìn có tên là v_nhan_vien để lấy được thông tin của tất cả các nhân viên có địa chỉ là “Hải Châu”
-- và đã từng lập hợp đồng cho một hoặc nhiều khách hàng bất kì với ngày lập hợp đồng là “12/12/2019”.
create view v_nhan_vien as select nhan_vien.ma_nhan_vien, nhan_vien.ho_ten from nhan_vien 
where nhan_vien.ma_nhan_vien in (select nhan_vien.ma_nhan_vien from nhan_vien  
inner join hop_dong  on hop_dong.ma_nhan_vien = nhan_vien.ma_nhan_vien 
where year(ngay_lam_hop_dong)=2019 and month(ngay_lam_hop_dong)=12 and day(ngay_lam_hop_dong)=12 and nhan_vien.dia_chi like '%Hải Châu');
-- 22.Thông qua khung nhìn v_nhan_vien thực hiện cập nhật địa chỉ thành “Liên Chiểu” ối với tất 
-- cả các nhân viên được nhìn thấy bởi khung nhìn này
update v_nhan_vien
set address='Liên Chiểu';
-- 23.Tạo Stored Procedure sp_xoa_khach_hang dùng để xóa thông tin của một khách hàng nào đó với ma_khach_hang được
-- truyền vào như là 1 tham số của sp_xoa_khach_hang.
DELIMITER $$
create procedure sp_xoa_khach_hang(in customerId int)
begin
delete  from khach_hang where customerID= customerId;
end; $$
DELIMITER ;
CALL sp_xoa_khach_hang()