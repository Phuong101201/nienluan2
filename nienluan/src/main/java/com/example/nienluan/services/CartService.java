package com.example.nienluan.services;

import com.example.nienluan.dto.AddItems;
import com.example.nienluan.exceptions.AppException;
import com.example.nienluan.models.Cart;
import com.example.nienluan.models.CartItem;
import com.example.nienluan.models.Phone;
import com.example.nienluan.models.User;
import com.example.nienluan.repository.CartItemRepository;
import com.example.nienluan.repository.CartRepository;
import com.example.nienluan.repository.PhoneRepository;
import com.example.nienluan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class CartService {

  @Autowired
  private CartItemRepository cartItemRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PhoneRepository phoneRepository;

  @Autowired
  private CartRepository cartRepository;
  public Cart getBasket(int user) throws ExecutionException, InterruptedException {
    System.out.println(user);
    Optional<Cart> existingBasket = cartRepository.findById(user);
    return existingBasket.isPresent() && existingBasket.get() != null ? existingBasket.get() : null;
  }

  public CartItem findBasketItemById(Set<CartItem> basketItems, String targetId) {
    for (CartItem a :
            basketItems) {
      if (a.getPhone().getName().equalsIgnoreCase(targetId)) {
        return a;
      }
    } // Trả về phần tử có id khớp hoặc null nếu không tìm thấy
    return null;
  }

  public CartItem findBasketItemById(Set<CartItem> basketItems, int targetId) {
    for (CartItem a :
            basketItems) {
      if (a.getId() ==(targetId)) {
        return a;
      }
    } // Trả về phần tử có id khớp hoặc null nếu không tìm thấy
    return null;
  }
  public void add(AddItems req) {
    Optional<User> existUser = userRepository.findByUsername(req.getUsername());
    Optional<Cart> existingBasket = cartRepository.findByUser(existUser.get());
    Optional<Phone> existPhone = phoneRepository.findByName(req.getName());
    if (existingBasket.isEmpty()) {
      Cart basket = Cart.builder().user(existUser.get()).totalItems(0).totalPrices(0L).cartItem(new HashSet<>()).build();
      cartRepository.save(basket);
      existingBasket = cartRepository.findByUser(existUser.get());
    }
    Set<CartItem> basketItems = new HashSet<>();
    if (existingBasket.get().getCartItem() == null || existingBasket.get().getCartItem().isEmpty()) {
      CartItem cartItem = CartItem.builder().cart(existingBasket.get()).phone(existPhone.get()).quantity(1).totalPrice(req.getPrice()).build();
      basketItems.add(cartItem);
      cartItemRepository.save(cartItem);
    } else {
      basketItems = existingBasket.get().getCartItem();
      CartItem basketItem = findBasketItemById(existingBasket.get().getCartItem(), req.getName());
      if (basketItem != null) {
        basketItems.remove(basketItem);
        basketItem.setQuantity(basketItem.getQuantity() + 1);
        cartItemRepository.save(basketItem);
        basketItems.add(basketItem);
        List<CartItem> basketList = new ArrayList<>(basketItems);

        // Sử dụng Comparator để sắp xếp danh sách theo ID tăng dần
        Collections.sort(basketList, Comparator.comparing(CartItem::getId));

        // Chuyển danh sách trở lại thành một Set
        basketItems = new LinkedHashSet<>(basketList);

      } else {
        CartItem cartItem = CartItem.builder().cart(existingBasket.get()).phone(existPhone.get()).quantity(1).totalPrice(req.getPrice()).build();
        basketItems.add(cartItem);
        cartItemRepository.save(cartItem);
      }

    }
    long total = 0;
    for (CartItem a :
            basketItems) {
      total += a.getPhone().getPrice() * a.getQuantity();
    }
    existingBasket.get().setTotalPrices(total);
    existingBasket.get().setTotalItems(basketItems.size());
    existingBasket.get().setCartItem(basketItems);

    cartRepository.save(existingBasket.get());
  }

  public String delete(Integer name) {
    cartRepository.deleteById(name);

    return String.format("%s is remove", name);
  }

  public void remove(AddItems req) {
    Optional<User> existUser = userRepository.findByUsername(req.getUsername());
    Optional<Cart> existingBasket = cartRepository.findByUser(existUser.get());
    if (existingBasket.isEmpty()) {
      throw new AppException("Khong co gio hang", HttpStatus.NOT_FOUND);
    }
    Cart basket = existingBasket.get();
    CartItem basketItem = findBasketItemById(basket.getCartItem(), req.getName());
    basketItem.setQuantity(basketItem.getQuantity() - 1);
    basket.setTotalPrices(basket.getTotalPrices() - req.getPrice());
    if (basketItem.getQuantity() == 0) {
      basketItem.setQuantity(basketItem.getQuantity() + 1);
      basket.getCartItem().remove(basketItem);
    }
    cartRepository.save(basket);
  }

  public String removeItem(int id, int name) {
    Optional<Cart> existingBasket = cartRepository.findById(name);
    if (existingBasket.isEmpty()) {
      throw new AppException("Khong co gio hang", HttpStatus.NOT_FOUND);
    }
    Set<CartItem> basketItems = existingBasket.get().getCartItem();
    CartItem basketItem = findBasketItemById(basketItems,id);
    if(basketItem != null){
      basketItems.remove(basketItem);
    }else {
      throw new AppException("Sản phẩm không có trong giỏ hàng", HttpStatus.NOT_FOUND);
    }
    existingBasket.get().setCartItem(basketItems);
    existingBasket.get().setTotalPrices(existingBasket.get().getTotalPrices()- basketItem.getTotalPrice()* basketItem.getQuantity());
    cartRepository.save(existingBasket.get());
    return String.format("%s đã được xóa", name);
  }

}
