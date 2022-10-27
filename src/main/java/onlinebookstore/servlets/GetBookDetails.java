package onlinebookstore.servlets;

import onlinebookstore.models.BookDTO;
import onlinebookstore.models.CategoryDTO;
import onlinebookstore.service.BookStoreManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetBookDetails extends HttpServlet {

    static BookStoreManager manager = BookStoreManager.getInstance();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            //get book id parameter
            String idParam = request.getParameter("id");
            request.getSession().setAttribute("manager", manager);
            System.out.println(idParam);

            //if book id is provided, set it with provided value
            if (!(idParam == null || idParam.isEmpty())) {
                //and set session variable with book object of specified id
                BookDTO book = manager.getBook(idParam);
                request.getSession().setAttribute("book", book);

                //find and set session variable with category for this book
                CategoryDTO category = manager.getCategory(book.getCategoryId());
                request.getSession().setAttribute("category", category);

                //get all ccomments
                ArrayList comments = manager.getBookComments(idParam);
                request.getSession().setAttribute("comments", comments);

                //get authors for book comments
                ArrayList authors = manager.getUserNamesByBookComments(idParam);
                request.getSession().setAttribute("authors", authors);

                //forward request to list_categories page
                request.getRequestDispatcher("get_book_details.jsp").forward(request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();
            //if error occurs, display error details
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
