package service;

import entity.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class ReviewService {

    @PersistenceContext(unitName = "onlinebookPU")
    private EntityManager em;

    // 🔷 ADD REVIEW
    public void addReview(Users user, Books book, int rating, String reviewText) {

        Review r = new Review();
        r.setUserId(user);
        r.setBookId(book);
        r.setRating(rating);
        r.setReviewText(reviewText);

        em.persist(r);
    }

    // 🔷 GET REVIEWS BY BOOK
   public List<Review> getReviewsByBook(Books book) {
    return em.createQuery(
        "SELECT r FROM Review r WHERE r.bookId = :b",
        Review.class)
        .setParameter("b", book)
        .getResultList();
}
}