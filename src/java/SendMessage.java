

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @Naimul Haque
 */
@WebServlet(urlPatterns = {"/Add"})
public class SendMessage extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession ses = request.getSession(true);
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        boolean isValid = false;
        
        ArrayList<User> users  = Admin.users;
        
        User user = new User(name,email);
        
        for(User temp: users){
            if(temp.isEqual(user)){
                isValid = true;
                break;
            }
        }
        
        
        Integer hit = (Integer)ses.getAttribute("hit");
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {

            if (isValid) {
                if (hit == null) {
                    hit = 1;
                    ses.setAttribute("hit", hit);
                } else {
                    hit = new Integer(hit + 1);
                    ses.setAttribute("hit", hit);
                }
                /* TODO output your page here. You may use following sample code. */
                out.println("<p>You sent: " + hit + " idea<br>");
                out.println("Hi, "+user.name+"!<br>");
                out.println("Your last message: <br>");
                out.println(message+"<br>");
                out.println("You sent " + hit + " ideas</p>");
                
                
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            } else {
                /* TODO output your page here. You may use following sample code. */
                out.println("<h3>Sorry, " + name + ", we couldn't recognise you! </h3>");
                out.println("<p>Sorry, "+name+". Please, register first! </p>");   
                RequestDispatcher rd = request.getRequestDispatcher("profiles.html");
                rd.include(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    public static String writeFile(String fileName,String content) throws FileNotFoundException, IOException{
        
        FileWriter fstream = new FileWriter(fileName);
        BufferedWriter out = new BufferedWriter(fstream);

        
        out.write("" + content);
        out.flush();
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = in.readLine(); // <-- read whole line
        
        return line;

    }
}
