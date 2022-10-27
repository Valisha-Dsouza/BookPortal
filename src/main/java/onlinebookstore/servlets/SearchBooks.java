package onlinebookstore.servlets;

import onlinebookstore.service.BookStoreManager;
import onlinebookstore.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchBooks extends HttpServlet {

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

            String title = Utils.process(request.getParameter("title"));
            String author = Utils.process(request.getParameter("author"));
            String categoryParam = request.getParameter("category");
            String pageParam = request.getParameter("page");

            //declare variables to pass to session variable

            Integer page = null;


            if ("0".equals(categoryParam)){
                categoryParam = null;
            }


            //if page number not provided, set it to 1
            if (pageParam == null || pageParam.isEmpty()) {
                page = new Integer(1);
            } else {
                //else, set it with provided value
                page = new Integer(pageParam);
            }

            //declare book collection to be displayed for specified search condition
            java.util.ArrayList books = manager.searchBooks(title, author, categoryParam, new Integer(getServletContext().getInitParameter("pageSize")));

            //store collection to session variable
            request.getSession().setAttribute("page", page);
            request.getSession().setAttribute("books", books);
            request.getSession().setAttribute("numberOfPages", new Integer(books.size()));

            //build category site URL
            String URL = "search.jsp?title=" + (request.getParameter("title") == null ? "" : request.getParameter("title"))
                    + "&author=" + (request.getParameter("author") == null ? "" : request.getParameter("author"))
                    + "&category=" + (categoryParam == null ? "" : categoryParam);

            //set URL to session variable
            request.getSession().setAttribute("URL", URL);
            request.getRequestDispatcher("search_books.jsp").forward(request, response);
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
