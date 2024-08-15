/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ServletPackages;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author AnhVuNAV
 */
@WebServlet("/firstServlet")
public class FirstServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FristServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FristServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        //get form data
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        String g = request.getParameter("gender");
        String t = request.getParameter("type");
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        StringBuilder message = new StringBuilder();
        
        //username dai it nhat 4 ki tu
        //pass dai 8-16 ki tu co ca so và chua cai
        //neu type la admin -> 1 user duy nhat (admin) => thong bao loi
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        if(u.length() < 4){
            message.append("<p> Username must at least 4 character </p>");
        }
        
        if (!p.matches(regex)) {
            message.append("<p> Password must contain at least one letter, one number and be between 8 and 16 characters long. </p>");
        }
        
        if(t.equalsIgnoreCase("Admin") && !u.equalsIgnoreCase("admin")){
            message.append("<p> Admin has only one user: Admin </p>");
        }
                
        
        if(message.isEmpty()){
            out.println("<h1>Your information:</h1>");
            out.println("<p> Username: " + u + "</p>");
            out.println("<p> Password: " + p + "</p>");
            out.println("<p> Gender: " + g + "</p>");
            out.println("<p> Type: " + t + "</p>");
        }else{
            out.println("<h1>Validation Errors:</h1>");
            out.println(message.toString());
        }
        out.println("</body></html>");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
