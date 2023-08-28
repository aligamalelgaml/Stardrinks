package fastrack.stardrinks.controller;

import fastrack.stardrinks.model.Order;
import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@BasePathAwareController
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/menu")
    public ResponseEntity<List<Product>> getMenu() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }
}
