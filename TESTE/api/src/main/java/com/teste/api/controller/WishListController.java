package com.teste.api.controller;


import com.teste.api.dto.wishproduct.WishProductRequestDTO;
import com.teste.api.service.WishListService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addProduct(@RequestBody WishProductRequestDTO wishProductRequestDTO) {
        return wishListService.addProductToWishList(wishProductRequestDTO);
    }

    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestParam(name = "userId") Long userId,
                              @RequestParam(name = "productCode") String productCode) {
        wishListService.removeProductFromWishList(userId, productCode);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getList(@RequestParam(name = "id") Long userId) {
        return wishListService.getWishProducts(userId);
    }
}
