package fastrack.stardrinks.controller;

import fastrack.stardrinks.model.Order;
import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.model.base.ResourceType;
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
import java.util.Map;

@BasePathAwareController
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<Map<ResourceType, List<? extends Product>>> getMenu() {
        return new ResponseEntity<>(this.productService.getMenu(), HttpStatus.OK);
    }
}
