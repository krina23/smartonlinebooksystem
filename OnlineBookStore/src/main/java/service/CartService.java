package service;

import entity.Books;
import entity.Cart;
import entity.CartItems;
import entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class CartService {

    @PersistenceContext(unitName = "onlinebookPU")
    private EntityManager em;

    // 🔷 GET OR CREATE CART
    public Cart getCartByUser(Users user) {

        List<Cart> list = em.createQuery(
                "SELECT c FROM Cart c WHERE c.userId = :u",
                Cart.class)
                .setParameter("u", user)
                .getResultList();

        if (list.isEmpty()) {
            Cart cart = new Cart();
            cart.setUserId(user);
            em.persist(cart);
            return cart;
        }

        return list.get(0);
    }

    // 🔷 ADD TO CART
   // 🔷 ADD TO CART (WITH STOCK CHECK)
public String addToCart(Users user, Books book) {

    // 🔴 CHECK STOCK AVAILABLE
    if (book.getStockQuantity() <= 0) {
        return "Out of Stock";
    }

    Cart cart = getCartByUser(user);

    List<CartItems> items = em.createQuery(
            "SELECT ci FROM CartItems ci WHERE ci.cartId = :c AND ci.bookId = :b",
            CartItems.class)
            .setParameter("c", cart)
            .setParameter("b", book)
            .getResultList();

    if (!items.isEmpty()) {

        CartItems item = items.get(0);

        // 🔴 CHECK STOCK LIMIT
        if (item.getQuantity() >= book.getStockQuantity()) {
            return "Maximum stock reached";
        }

        item.setQuantity(item.getQuantity() + 1);
        em.merge(item);

    } else {

        CartItems item = new CartItems();
        item.setCartId(cart);
        item.setBookId(book);
        item.setQuantity(1);
        em.persist(item);
    }

    return "Added to Cart";
}

    // 🔷 GET CART ITEMS
    public List<CartItems> getCartItems(Users user) {
        Cart cart = getCartByUser(user);

        return em.createQuery(
                "SELECT ci FROM CartItems ci WHERE ci.cartId = :c",
                CartItems.class)
                .setParameter("c", cart)
                .getResultList();
    }

    // 🔷 GET ITEM BY ID
    public CartItems getCartItemById(int id) {
        return em.find(CartItems.class, id);
    }

    // 🔷 UPDATE ITEM
    public void updateItem(CartItems item) {
        em.merge(item);
    }

    // 🔷 REMOVE ITEM
    public void removeItem(int id) {
        CartItems item = em.find(CartItems.class, id);
        if (item != null) {
            em.remove(item);
        }
    }
}