delimiter //
create trigger delete_product_brand
before delete on product_brand for each row
begin
	delete from product where product_brand_id = OLD.id;
end //
delimiter ;

drop trigger if exists  delete_product_order_detail;
delimiter //
create trigger delete_product_order_detail
before truncate on product for each row
begin
	delete from order_detail where product_id = OLD.id;
end 

-- Hàm xóa 
delimiter ;
drop trigger if exists  delete_product_brand_product;
delimiter //
create trigger delete_product_brand_product
before truncate on product_brand for each row
begin
	delete from product where product_brand_id = OLD.id;
end 
delimiter ;



--  
SET FOREIGN_KEY_CHECKS = 0;
truncate table order_detail;
truncate table product;
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
truncate table product;
truncate table product_brand;
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
truncate table product;
truncate table category;
SET FOREIGN_KEY_CHECKS = 1;

delete from product_brand where id = 3;
truncate table product;
truncate table order_detail;
select * from product;
delete from product;