package com.samfrosh.controller;

import com.samfrosh.model.Product;
import com.samfrosh.exception.ProductNotFound;
import com.samfrosh.service.ProductsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductsService productsService;

//    @Async                       //Asynchronous method
//    @GetMapping("/getProductById/{id}")
//    public CompletableFuture<ResponseEntity<List<Product>>> getProductById(@PathVariable(value = "id") Long id) throws ProductNotFound {
//        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.OK).body(productsService.getProductById(id)));
//    }

    @GetMapping("/bystatus/{id}/{page}/{size}")
    public ResponseEntity<Page<Product>> getProductByStatusId(@PathVariable(value = "id") Long id,
                                                              @PathVariable(value = "page") int page,
                                                              @PathVariable(value = "size") int size) throws ProductNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductByStatusId(id, page, size));
    }

    @GetMapping("/search/{name}/{page}/{size}")
    public ResponseEntity<Page<Product>> searchProductByName(@PathVariable("name") String name,
                                                             @PathVariable("page") int page,
                                                             @PathVariable("size") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.searchProductByName(name, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductById(id));
    }

    @GetMapping("/bycategory/random/{id}")
    public ResponseEntity<List<Product>> getRandomProductByCategoryIdLimit(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getRandomProductByCategoryIdLimit(id));
    }

    @GetMapping("/bystatus/random/{id}")
    public ResponseEntity<List<Product>> getRandomProductByStatusIdLimit(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductByStatusIdLimit(id));
    }


}
