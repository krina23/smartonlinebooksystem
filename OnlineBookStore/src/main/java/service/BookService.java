package service;

import entity.Books;
import entity.Category;
import entity.Author;
import entity.Users;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class BookService {

    @PersistenceContext(unitName = "onlinebookPU")
    private EntityManager em;

    // 🔷 ADD BOOK
    public String addBook(Books book) {
    try {
        // 🔷 TITLE VALIDATION
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            return "Book title is required";
        }

        // 🔷 ISBN VALIDATION
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            return "ISBN is required";
        }

        // 🔷 PRICE VALIDATION
        if (book.getPrice() == null || book.getPrice().doubleValue() <= 0) {
            return "Valid price required";
        }

        // 🔷 STOCK VALIDATION
        if (book.getStockQuantity() == null || book.getStockQuantity() < 0) {
            return "Stock must be valid";
        }

        // 🔷 IMAGE VALIDATION
        if (book.getImageUrl() == null || book.getImageUrl().isEmpty()) {
            return "Image is required";
        }

        // 🔷 SAVE
        em.persist(book);

        return "Book added successfully";

    } catch (Exception e) {
        e.printStackTrace();
        return "Error adding book";
    }
}

    // 🔷 UPDATE BOOK
    public String updateBook(Books book) {
        try {
            em.merge(book);
            return "Book updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating book";
        }
    }

    // 🔷 DELETE BOOK
    public String deleteBook(int id) {
        try {
            Books book = em.find(Books.class, id);

            if (book == null) {
                return "Book not found";
            }

            em.remove(book);
            return "Book deleted successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting book";
        }
    }

    // 🔷 GET ALL BOOKS
    public List<Books> getAllBooks() {
        return em.createQuery("SELECT b FROM Books b", Books.class)
                 .getResultList();
    }

    // 🔷 GET BOOK BY ID
    public Books getBookById(int id) {
        return em.find(Books.class, id);
    }

    // 🔷 SEARCH BOOKS (by title)
    public List<Books> searchBooks(String keyword) {
        return em.createQuery(
                "SELECT b FROM Books b WHERE b.title LIKE :key",
                Books.class)
                .setParameter("key", "%" + keyword + "%")
                .getResultList();
    }

    // 🔷 FILTER BY CATEGORY
    public List<Books> getBooksByCategory(int categoryId) {
        return em.createQuery(
                "SELECT b FROM Books b WHERE b.categoryId.categoryId = :cid",
                Books.class)
                .setParameter("cid", categoryId)
                .getResultList();
    }
    
   public List<Books> getRecommendedBooks(Users user) {

    // 🔷 Step 1: Get categories user purchased
    List<Integer> categoryIds = em.createQuery(
        "SELECT DISTINCT oi.bookId.categoryId.categoryId " +
        "FROM OrderItems oi WHERE oi.orderId.userId = :user",
        Integer.class)
        .setParameter("user", user)
        .getResultList();

    // 🔷 If no purchase → show random books
    if (categoryIds.isEmpty()) {
        return em.createQuery(
            "SELECT b FROM Books b",
            Books.class)
            .setMaxResults(5)
            .getResultList();
    }

    // 🔷 Step 2: Recommend books from same category but NOT purchased
    return em.createQuery(
        "SELECT b FROM Books b WHERE b.categoryId.categoryId IN :catIds " +
        "AND b.bookId NOT IN (" +
        " SELECT oi.bookId.bookId FROM OrderItems oi WHERE oi.orderId.userId = :user" +
        ")",
        Books.class)
        .setParameter("catIds", categoryIds)
        .setParameter("user", user)
        .setMaxResults(5)
        .getResultList();
}
}