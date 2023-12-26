package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Product;
import ra.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/productController")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView("products");
        List<Product> listProducts = productService.findAll();
        mav.addObject("listProducts", listProducts);
        return mav;
    }
    @PostMapping("/create")
    public String createProduct(Product productNew, MultipartFile avatar, MultipartFile[] otherImages){
        boolean result = productService.save(productNew,avatar,otherImages);
        if (result){
            return "redirect:findAll";
        }else{
            return "error";
        }

    }

}
