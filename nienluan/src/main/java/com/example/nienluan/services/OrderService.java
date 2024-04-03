package com.example.nienluan.services;


import com.example.nienluan.Utils.OrderStatus;
import com.example.nienluan.dto.OrderRequest;
import com.example.nienluan.exceptions.AppException;
import com.example.nienluan.models.Cart;
import com.example.nienluan.models.CartItem;
import com.example.nienluan.models.Order;
import com.example.nienluan.models.Paymentmethod;
import com.example.nienluan.models.User;
import com.example.nienluan.repository.CartRepository;
import com.example.nienluan.repository.OrderRepository;
import com.example.nienluan.repository.PaymentmethodRepository;
import com.example.nienluan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.List;
@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private PaymentmethodRepository paymentMethodRepository;
  @Autowired
  private CartRepository cartRepository;
@Autowired
private UserRepository userRepository;
  public List<Order> getListOrder() {
    return orderRepository.findAll();
  }


  public CartItem findOrderItemById(Set<CartItem> orderItems, String targetId) {
    for (CartItem a :
            orderItems) {
      if (a.getPhone().getName().equalsIgnoreCase(targetId)) {
        return a;
      }
    } // Trả về phần tử có id khớp hoặc null nếu không tìm thấy
    return null;
  }

  public CartItem findOrderItemById(Set<CartItem> orderItems, int targetId) {
    for (CartItem a :
            orderItems) {
      if (a.getId() == (targetId)) {
        return a;
      }
    } // Trả về phần tử có id khớp hoặc null nếu không tìm thấy
    return null;
  }

  public long totalPriceCal(Set<CartItem> orderItems) {
    long totalPrice = 0;
    for (CartItem orderItem : orderItems) {
      totalPrice += orderItem.getTotalPrice() * orderItem.getQuantity();
    }
    return totalPrice;

  }

  public void addOrder(OrderRequest orderRequest) {
    Optional<Paymentmethod> paymentMethod = paymentMethodRepository.findById(orderRequest.getPaymentMethod());
    Order order = Order.builder()
            .address(orderRequest.getAddress())
            .receiver(orderRequest.getName())
            .paymentmethod(paymentMethod.get())
            .createdDate(LocalDateTime.now())
            .totalPrice(totalPriceCal(orderRequest.getItems()))
            .isPaid(orderRequest.isPaid())
            .username(orderRequest.getUsername())
            .email(orderRequest.getEmail())
            .status(OrderStatus.PENDING)
            .no(UUID.randomUUID().toString())
            .phoneNumber(orderRequest.getPhoneNumber()).build();
    orderRepository.save(order);

    Optional<Order> orderOptional = orderRepository.findByNo(order.getNo());
    System.out.println(orderOptional.get());
    for (CartItem orderItem : orderRequest.getItems()) {
      orderOptional.get().getCartItems().add(orderItem);
    }
    orderRepository.save(orderOptional.get());

    cartRepository.delete(cartRepository
            .findByUser(userRepository
                    .findByUsername(orderRequest
                            .getUsername()).get()).get());
  }

  public void updateOrder(OrderRequest orderRequest) {
    Optional<Order> order = orderRepository.findByUsername(orderRequest.getUsername());
    if (order.isEmpty()) {
      throw new AppException("Không tìm thấy hóa đơn", HttpStatus.NOT_FOUND);
    }
    Order order1 = order.get();
    order1.setUsername(orderRequest.getUsername());

    orderRepository.save(order1);
  }

  public void deleteOrder(Integer id) {
    Optional<Order> orderOptional = orderRepository.findById(id);
    if (orderOptional.isEmpty()) {
      throw new AppException("không tìm thấy hóa đơn", HttpStatus.NOT_FOUND);
    }
    orderRepository.delete(orderOptional.get());
  }
  public Map<String, BigDecimal> getOrdersOrderByCreateDate() {
    Map<String, BigDecimal> revenueByMonth = new HashMap<>();
    List<Order> allOrders = orderRepository.findAllByStatusAndIsPaid(OrderStatus.RECEIVED, true);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    for (Order order : allOrders) {
      // Chuyển đổi LocalDateTime thành String sử dụng DateTimeFormatter
      String orderDateString = order.getCreatedDate().format(formatter);

      // Parse String thành LocalDateTime
      LocalDateTime orderDate = LocalDateTime.parse(orderDateString, formatter);

      // Tiếp tục xử lý như trước
      String monthYear = orderDate.getMonth().toString() + " " + orderDate.getYear();
      revenueByMonth.merge(monthYear, new BigDecimal(order.getTotalPrice()), BigDecimal::add);
    }
    return revenueByMonth;
  }
  public void updateStatusOrder(int id, int value) {
    Optional<Order> orderOptional = orderRepository.findById(id);

    if (orderOptional.isEmpty()) {
      throw new AppException("Không tìm thấy order", HttpStatus.NOT_FOUND);
    }
    Order order = orderOptional.get();
    if(order.getStatus() == OrderStatus.RECEIVED){
      throw new AppException("Không thể cập nhật trạng thái giao dịch này", HttpStatus.BAD_REQUEST);
    }
    switch (value) {
      case 0:
        order.setStatus(OrderStatus.CANCEL);
        break;
      case 1:
        order.setStatus(OrderStatus.SHIPPING);
        break;
      case 2:
        order.setStatus(OrderStatus.RECEIVED);
        order.setPaid(true);
        break;
      default:
        break;
    }
    orderRepository.save(order);
  }

  public List<Order> getListOrderByStatus(int status) {
    List<Order> orders;
    switch (status) {
      case 0:
        orders = orderRepository.findAllByStatus(OrderStatus.CANCEL);
        break;
      case 3:
        orders = orderRepository.findAllByStatus(OrderStatus.PENDING);
        break;
      case 1:
        orders = orderRepository.findAllByStatus(OrderStatus.SHIPPING);
        break;
      case 2:
        orders = orderRepository.findAllByStatus(OrderStatus.RECEIVED);
        break;
      default:
        throw new AppException("Trạng thái không hợp lệ", HttpStatus.BAD_REQUEST);
    }
    return orders;
  }

  public List<Order> getListOrderByUser(String user) {
    Optional<List<Order>> orders = orderRepository.findAllByUsername(user);
    if(orders.isEmpty()){
      return null;
    }
    return orders.get();
  }
}
