package service;

import entity.Books;
import entity.Users;
import entity.Wishlist;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class WishlistService {

    @PersistenceContext(unitName = "onlinebookPU")
    private EntityManager em;

    // 🔷 ADD TO WISHLIST
    public void addToWishlist(Users user, Books book) {

        List<Wishlist> list = em.createQuery(
                "SELECT w FROM Wishlist w WHERE w.userId = :u AND w.bookId = :b",
                Wishlist.class)
                .setParameter("u", user)
                .setParameter("b", book)
                .getResultList();

        if (list.isEmpty()) {
            Wishlist w = new Wishlist();
            w.setUserId(user);
            w.setBookId(book);
            em.persist(w);
        }
    }

    // 🔷 GET WISHLIST
    public List<Wishlist> getWishlist(Users user) {
        return em.createQuery(
                "SELECT w FROM Wishlist w WHERE w.userId = :u",
                Wishlist.class)
                .setParameter("u", user)
                .getResultList();
    }

    // 🔷 REMOVE ITEM
    public void remove(int id) {
        Wishlist w = em.find(Wishlist.class, id);
        if (w != null) {
            em.remove(w);
        }
    }
}