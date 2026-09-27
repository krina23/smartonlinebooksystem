package service;

import entity.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Stateless
public class OrderService {

    @PersistenceContext(unitName = "onlinebookPU")
    private EntityManager em;

    // 🔥 PLACE ORDER
    public void placeOrder(Users user, List<CartItems> cartItems,
                       String fullAddress, String city, String state, int pincode) {

    // 🔷 SAVE ADDRESS
    UserAddresses address = new UserAddresses();
    address.setUserId(user);
    address.setFullAddress(fullAddress);
    address.setCity(city);
    address.setState(state);
    address.setPincode(pincode);

    em.persist(address);

    // 🔷 CREATE ORDER
    Orders order = new Orders();
    order.setUserId(user);
    order.setAddressId(address);   // ✅ NOW LINKED
    order.setOrderStatus("Placed");
    order.setOrderDate(new java.util.Date());

    java.math.BigDecimal total = java.math.BigDecimal.ZERO;

    for (CartItems c : cartItems) {
        java.math.BigDecimal price = c.getBookId().getPrice();
        java.math.BigDecimal qty = new java.math.BigDecimal(c.getQuantity());
        total = total.add(price.multiply(qty));
    }

    order.setTotalAmount(total);

    em.persist(order);

    // 🔷 SAVE ORDER ITEMS
    for (CartItems c : cartItems) {

        OrderItems item = new OrderItems();
        item.setOrderId(order);
        item.setBookId(c.getBookId());
        item.setQuantity(c.getQuantity());
        item.setPrice(c.getBookId().getPrice());

        em.persist(item);

        // 🔥 CLEAR CART
        em.remove(em.merge(c));
    }
}
    
    public void placeSingleOrder(Users user, CartItems c,
        String fullAddress, String city, String state, int pincode) {

    // 🔷 SAVE ADDRESS
    UserAddresses address = new UserAddresses();
    address.setUserId(user);
    address.setFullAddress(fullAddress);
    address.setCity(city);
    address.setState(state);
    address.setPincode(pincode);

    em.persist(address);

    // 🔷 CREATE ORDER
    Orders order = new Orders();
    order.setUserId(user);
    order.setAddressId(address);
    order.setOrderStatus("Placed");
    order.setOrderDate(new java.util.Date());

    java.math.BigDecimal total =
            c.getBookId().getPrice().multiply(new java.math.BigDecimal(c.getQuantity()));

    order.setTotalAmount(total);

    em.persist(order);

    // 🔷 ORDER ITEM
    OrderItems item = new OrderItems();
    item.setOrderId(order);
    item.setBookId(c.getBookId());
    item.setQuantity(c.getQuantity());
    item.setPrice(c.getBookId().getPrice());

    em.persist(item);

    // 🔥 REMOVE FROM CART
    em.remove(em.merge(c));
}
    
   public List<Orders> getOrdersByUser(Users user) {
    return em.createQuery(
        "SELECT DISTINCT o FROM Orders o " +
        "LEFT JOIN FETCH o.orderItemsCollection oi " +
        "LEFT JOIN FETCH oi.bookId " +
        "WHERE o.userId = :u ORDER BY o.orderDate DESC",
        Orders.class)
        .setParameter("u", user)
        .getResultList();
}
}