

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author jeel
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {

   

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = java.net.URLDecoder.decode(request.getParameter("name"), "UTF-8");
        
           //ADD THIS LINE HERE
    System.out.println("Requested file: " + fileName);

        String path = System.getProperty("user.home") + File.separator + "bookstore_images";
        File file = new File(path, fileName);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("image/jpeg");
        Files.copy(file.toPath(), response.getOutputStream());
    }
        
    

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}