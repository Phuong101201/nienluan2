package com.example.nienluan.controllers;

import com.example.nienluan.dto.OrderRequest;
import com.example.nienluan.models.Order;
import com.example.nienluan.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderService orderService;
  @GetMapping
  public ResponseEntity<List<Order>> orderList(){
    return ResponseEntity.ok(orderService.getListOrder());
  }

  @GetMapping("/createDate")
  public Map<String, BigDecimal> getOrdersOrderByCreateDate(){
    return orderService.getOrdersOrderByCreateDate();
  }
  @GetMapping("/private/{user}")
  public ResponseEntity<List<Order>> orderListByUser(@PathVariable String user){
    return ResponseEntity.ok(orderService.getListOrderByUser(user));
  }
  @PostMapping
  public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest){
    orderService.addOrder(orderRequest);
    return ResponseEntity.ok("Tạo order thành công ");
  }
  @GetMapping("/{status}")
  public List<Order> getOrderByStatus(@PathVariable int status){
    return orderService.getListOrderByStatus(status);
  }
  @PutMapping("/{id}/{state}")
  public ResponseEntity updateStatusOrder(@PathVariable int id, @PathVariable int state){
    orderService.updateStatusOrder(id, state);
    return ResponseEntity.ok("Cập nhật trạng thái đơn hàng thành công");
  }


  @DeleteMapping("/{id}")
  public ResponseEntity deleteOrder(@PathVariable Integer id){
    orderService.deleteOrder(id);
    return  ResponseEntity.ok("Xóa tác giả thành công ");
  }

  @PutMapping
  public ResponseEntity updateOrder(@RequestBody OrderRequest orderRequest){
    orderService.updateOrder(orderRequest);
    return ResponseEntity.ok("Cập nhật tác giả thành công ");
  }
}
