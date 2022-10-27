package onlinebookstore.servlets;

import onlinebookstore.models.CommentDTO;
import onlinebookstore.models.UserDTO;
import onlinebookstore.service.BookStoreManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AddComment extends HttpServlet {

    static BookStoreManager manager;

    static {
        manager = new BookStoreManager();
    }

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
        response.sendRedirect("index.jsp");
        response.setContentType("text/html;charset=UTF-8");
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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //get session control

            //get parameters
            String ratingParam = request.getParameter("rating");
            String contentParam = request.getParameter("content");
            String bookIdParam = request.getParameter("bookId");


            //declare variables to be set as session variables
            Integer rating = new Integer(ratingParam);
            String content = contentParam;
            String userId = ((UserDTO) request.getSession().getAttribute("user")).getId();


            //decalre data transfer object, ready for transaction
            CommentDTO comment = new CommentDTO(userId, bookIdParam, rating, content);

            //attempt to add comment
            manager.addComment(comment);

            //if no error occurs, display confirmation message and return to book details page
            out.println("<script type='text/javascript'>");
            out.println("alert('Successfully added new comment!');");
            out.println("window.location = 'details.jsp?id=" + bookIdParam + "';");
            out.println("</script>");
        } catch (Exception e) {
            //if error occurs, display error details
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        } finally {
            out.close();
        }

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
