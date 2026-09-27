package servlet;

import entity.Books;
import entity.Review;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.BookService;
import java.lang.String;
import java.math.BigDecimal;
import java.io.IOException;
import java.io.File;
import java.util.List;
import service.ReviewService;

@MultipartConfig
@WebServlet(name = "BookServlet", urlPatterns = {"/books"})
public class BookServlet extends HttpServlet {

    @EJB
    private BookService bookService;
    
    @EJB
    private ReviewService reviewService;

    // 🔷 GET METHODS
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            List<Books> books = bookService.getAllBooks();
            request.setAttribute("bookList", books);
            request.getRequestDispatcher("bookList.jsp").forward(request, response);
        }

        else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            bookService.deleteBook(id);
            response.sendRedirect("books");
        }

         // 🔥 THIS IS YOUR UPDATED VIEW CODE
    else if ("view".equals(action)) {
        int id = Integer.parseInt(request.getParameter("id"));

        Books book = bookService.getBookById(id);
        request.setAttribute("book", book);

        // 🔥 LOAD REVIEWS
        List<Review> reviews = reviewService.getReviewsByBook(book);
        request.setAttribute("reviewList", reviews);

        request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
    }
        else if ("search".equals(action)) {
            String keyword = request.getParameter("keyword");
            List<Books> books = bookService.searchBooks(keyword);

            request.setAttribute("bookList", books);
            request.getRequestDispatcher("bookList.jsp").forward(request, response);
        }

        else {
            response.sendRedirect("books");
        }
    }

    // 🔷 POST METHODS
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            Books book = new Books();

            // 🔷 INPUTS
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String isbn = request.getParameter("isbn");
            String priceStr = request.getParameter("price");
            String stockStr = request.getParameter("stock");
            String pagesStr = request.getParameter("pages");

            // 🔷 VALIDATION
            if (title == null || title.trim().isEmpty()) {
                request.setAttribute("message", "Title is required");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            if (isbn == null || isbn.trim().isEmpty()) {
                request.setAttribute("message", "ISBN is required");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            if (priceStr == null || priceStr.trim().isEmpty()) {
                request.setAttribute("message", "Price is required");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            if (stockStr == null || stockStr.trim().isEmpty()) {
                request.setAttribute("message", "Stock is required");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            if (pagesStr == null || pagesStr.trim().isEmpty()) {
                request.setAttribute("message", "Pages are required");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            // 🔷 PARSE VALUES
            double price;
            int stock;
            int pages;

            try {
                price = Double.parseDouble(priceStr);
                stock = Integer.parseInt(stockStr);
                pages = Integer.parseInt(pagesStr);
            } catch (Exception e) {
                request.setAttribute("message", "Invalid numeric values");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }
            
            book.setTitle(title);
            book.setDescription(description);
            book.setIsbn(isbn);
            book.setPrice(BigDecimal.valueOf(price));
            book.setStockQuantity(stock);
            book.setPages(pages);

            if (price <= 0) {
                request.setAttribute("message", "Price must be greater than 0");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            if (stock < 0) {
                request.setAttribute("message", "Stock cannot be negative");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            if (pages <= 0) {
                request.setAttribute("message", "Pages must be greater than 0");
                request.getRequestDispatcher("addBook.jsp").forward(request, response);
                return;
            }

            // 🔥 IMAGE HANDLING
           // 🔥 IMAGE HANDLING
// 🔥 IMAGE HANDLING
// 🔥 IMAGE HANDLING
Part filePart = request.getPart("imageFile");
String fileName = filePart.getSubmittedFileName();

if (fileName == null || fileName.isEmpty()) {
    request.setAttribute("message", "Image is required");
    request.getRequestDispatcher("addBook.jsp").forward(request, response);
    return;
}

// unique name
fileName = System.currentTimeMillis() + "_" + fileName;

// same path as ImageServlet
String uploadPath = System.getProperty("user.home") + File.separator + "bookstore_images";

File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) {
    uploadDir.mkdirs();
}

// save file
filePart.write(uploadPath + File.separator + fileName);

// save in DB
book.setImageUrl(fileName);
            // 🔷 SAVE
            String result = bookService.addBook(book);

            request.setAttribute("message", result);

            // 🔷 CLEAR / RETAIN FORM
            if (result.equals("Book added successfully")) {
                request.setAttribute("title", "");
                request.setAttribute("description", "");
                request.setAttribute("isbn", "");
                request.setAttribute("price", "");
                request.setAttribute("stock", "");
                request.setAttribute("pages", "");
            } else {
                request.setAttribute("title", title);
                request.setAttribute("description", description);
                request.setAttribute("isbn", isbn);
                request.setAttribute("price", priceStr);
                request.setAttribute("stock", stockStr);
                request.setAttribute("pages", pagesStr);
            }

            request.getRequestDispatcher("addBook.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Book Servlet";
    }
}