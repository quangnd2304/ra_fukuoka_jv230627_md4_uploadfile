package ra.service;

import org.springframework.web.multipart.MultipartFile;
import ra.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    boolean save(Product product, MultipartFile avatar, MultipartFile[] otherImages);

    boolean update(Product product);

    boolean delete(String productId);
}
