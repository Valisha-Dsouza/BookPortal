package onlinebookstore.servlets;

import onlinebookstore.models.CategoryDTO;
import onlinebookstore.service.BookStoreManager;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ListCategoryBooks extends HttpServlet {

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
            request.getSession().setAttribute("manager", manager);
            String idParam = request.getParameter("id");
            String pageParam = request.getParameter("page");
            request.getSession().setAttribute("page", pageParam);

            if (!(idParam == null || idParam.isEmpty())) {
                CategoryDTO category = manager.getCategory(idParam);
                request.getSession().setAttribute("category", category);
            }

            //declare book collection to be displayed for specified category id and page number
            java.util.ArrayList books = manager.browseBooks(idParam, new Integer(getServletContext().getInitParameter("pageSize")));
            request.getSession().setAttribute("books", books);
            request.getSession().setAttribute("numberOfPages", new Integer(books.size()));

            if (StringUtils.isBlank(pageParam) || Integer.valueOf(pageParam) > books.size()) {
                request.getSession().setAttribute("page", new Integer(books.size()));
            }

            String URL = "category.jsp?id=" + (idParam == null ? "" : idParam);
            request.getSession().setAttribute("URL", URL);
            request.getRequestDispatcher("list_category_books.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
