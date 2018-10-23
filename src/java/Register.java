/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/SignUp"})
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<User> users  = Admin.users;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        HttpSession ses = request.getSession();
        
        String msg = "";
        
        User user = new User(name,email);
        
        boolean isValidToAdd = true;
        boolean validEmail = isValid(email);
        
        for(User temp: users){
            if(temp.isEqual(user)){
                isValidToAdd = false;
                break;
            }
            
        }
        
       
        try (PrintWriter out = response.getWriter()) {
            if (isValidToAdd && validEmail) {
                
               Admin.addUser(user);
               ses.setAttribute("name", user.name);
                /* TODO output your page here. You may use following sample code. */
                out.println("<br><br><br><br><p>Hi, : " + user.name+ " <br>");
                out.println("The sign up was succesful! </p>");
                

                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }else if(!validEmail){
                /* TODO output your page here. You may use following sample code. */
                out.println("<br><br><br><br>  <p> <font color=red> Invalid Email!</p><font color=black> ");

                RequestDispatcher rd = request.getRequestDispatcher("profiles.html");
                rd.include(request, response);
            } 
            else {
                /* TODO output your page here. You may use following sample code. */
                out.println("<br><br><br><br>  <p> <font color=red> The username and email is already used. </p><font color=black> ");

                RequestDispatcher rd = request.getRequestDispatcher("profiles.html");
                rd.include(request, response);
            }
        }
    }

    public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
