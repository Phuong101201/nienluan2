package com.example.nienluan.controllers;

import com.example.nienluan.dto.AddItems;
import com.example.nienluan.models.Cart;
import com.example.nienluan.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cart")
public class CartController {
  @Autowired
  private CartService cartService;

  @GetMapping("/{id}")
  public ResponseEntity<Cart> getCart(@PathVariable Integer id) throws ExecutionException, InterruptedException {
    return ResponseEntity.ok(cartService.getBasket(id));
  }

  @PostMapping("/add")
  public void addToCart(@RequestBody AddItems req){
    cartService.add(req);
  }

  @PutMapping("/remove")
  public void removeItem(@RequestBody AddItems req){
    cartService.remove(req);
  }
  @DeleteMapping("/remove/item/{id}")
  public String removeItem(@PathVariable int id, @RequestParam Integer name) {
    return cartService.removeItem(id,name);
  }
  @DeleteMapping("/delete/{name}")
  public String deleteCart(@PathVariable int name) {
    return cartService.delete(name);
  }
}
