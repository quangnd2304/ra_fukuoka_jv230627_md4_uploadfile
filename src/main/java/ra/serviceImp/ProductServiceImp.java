package ra.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.model.Images;
import ra.model.Product;
import ra.repository.ImagesRepository;
import ra.repository.ProductRepository;
import ra.service.ProductService;
import ra.service.UploadFileService;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean save(Product product, MultipartFile avatar, MultipartFile[] otherImages) {
        //1.upload avartar và otherImages lên firebase và lấy lại đường link
        //Upload ảnh avatar
        String avartar_link = uploadFileService.uploadFile(avatar);
        //2.set link vào product
        product.setAvatarImage(avartar_link);
        //3.thêm mới sản phẩm
        Product proNew = productRepository.save(product);
        //Thêm mới các ảnh phụ vào Images
        for (MultipartFile file : otherImages) {
            String link = uploadFileService.uploadFile(file);
            Images images = new Images();
            images.setImageLink(link);
            images.setProduct(proNew);
            imagesRepository.save(images);
        }
        return proNew != null ? true : false;
    }

    @Override
    public boolean update(Product product) {
        //Lấy Product
        Product productUpdate = productRepository.findById(product.getId()).get();
        productUpdate.setName(product.getName());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setAvatarImage(product.getAvatarImage());
        productUpdate.setStatus(product.isStatus());
        Product pro = productRepository.save(productUpdate);
        return pro != null ? true : false;
    }

    @Override
    public boolean delete(String productId) {
        boolean result = false;
        try {
            productRepository.deleteById(productId);
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
