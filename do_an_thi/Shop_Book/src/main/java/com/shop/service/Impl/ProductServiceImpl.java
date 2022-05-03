package com.shop.service.Impl;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private MultipartFile productAvatar;
    public static final String path_To_File = "F:\\Inter0322-NguyenThiHongNhung\\do_an_thi\\Shop_Book\\src\\main\\resources\\static\\images\\avatar\\";

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findProductByNameContaining(String name, Pageable pageable) {
        return productRepository.findProductByNameContaining("%"+name+"%", pageable);
    }
    @Override
//    public void addFile(Product productsEnity, MultipartFile productAvatar
//    ) throws IllegalStateException, IOException {
//
//        String pathToFile ="F:\\Inter0322-NguyenThiHongNhung\\do_an_thi\\Shop_Book\\src\\main\\resources\\static\\images\\avatar\\"
//                + productAvatar.getOriginalFilename();
//        // tạo file
//        File file = new File(pathToFile);
//        //chuyển data vào cái file vừa tạo
//        productAvatar.transferTo(new File(pathToFile));
//        //luu tên ảnh vào db
//        productsEnity.setAvatar(productAvatar.getOriginalFilename());
//        saveProduct(productsEnity);
//
//    }
    public void addFile(Product productsEnity, MultipartFile productAvatar
    ) throws IllegalStateException, IOException {
        String randomChar = RandomStringUtils.randomAlphabetic(10);
        // tạo file
        File file = new File(path_To_File + randomChar + productAvatar.getOriginalFilename());
        //chuyển data vào cái file vừa tạo
        productAvatar.transferTo(new File(path_To_File + randomChar + productAvatar.getOriginalFilename()));
        //luu tên ảnh vào db
        productsEnity.setAvatar(randomChar + productAvatar.getOriginalFilename());
        saveProduct(productsEnity);

    }

    @Override
        public void editFile(Product productsEnity, MultipartFile productAvatar) throws IllegalStateException, IOException {
            Optional<Product> product = productRepository.findById(productsEnity.getId());
            if (!productAvatar.isEmpty()) {
                new File(path_To_File + product.get().getAvatar()).delete();
                addFile(productsEnity, productAvatar);
            } else {

            }

        }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> searchByName(String nameProduct, Pageable pageable) {
        return productRepository.searchByName(nameProduct, pageable);
    }
        /*
        check xem có ảnh đó có muốn thay đổi hay không
        nếu có tìm cái ảnh cũ xóa nó đi : File(pathToFile).delete()
        thêm ảnh mới giống method addFile
         */






}
