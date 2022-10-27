package onlinebookstore.servlets;

import onlinebookstore.models.UserDTO;
import onlinebookstore.service.BookStoreManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Register extends HttpServlet {

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


            //update session variable
            request.getSession().setAttribute("manager", manager);


            //get parameters
            String loginParam = request.getParameter("login");
            String passwordParam = request.getParameter("password");
            String emailParam = request.getParameter("email");
            String nameParam = request.getParameter("name");
            String phoneParam = request.getParameter("phone");
            String birthdayParam = request.getParameter("birthday");

            //create new data transfer object, ready for registration
            UserDTO user = new UserDTO();

            //set user object fields with parameter value
            user.setLogin(loginParam);
            user.setPassword(passwordParam);
            user.setEmail(emailParam);
            user.setName(nameParam);
            if (!(phoneParam == null || phoneParam.isEmpty())) {
                user.setPhone(phoneParam);
            }
            if (!(birthdayParam == null || birthdayParam.isEmpty())) {
                user.setBirthday(new Date(birthdayParam));
            }

            //add new user to database
            manager.register(user);

            //if user created, display message dialog and redirect to homepage
            out.println("<script type='text/javascript'>");
            out.println("alert('Your account has been successfully generated!');");
            out.println("window.location = 'index.jsp';");
            out.println("</script>");
        } catch (Exception e) {
            System.out.println("Exception while registering:{}" + e.getMessage() + " " + e.getStackTrace());
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
