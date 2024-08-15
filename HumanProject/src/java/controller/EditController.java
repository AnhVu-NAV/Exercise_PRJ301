/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Human;
import model.HumanType;

/**
 *
 * @author AnhVuNAV
 */
public class EditController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        DBContext db = new DBContext();
        Human human = db.getHumanById(Integer.parseInt(id));
        ArrayList<HumanType> types = db.getTypes();

        request.setAttribute("human", human);
        request.setAttribute("types", types);
        request.getRequestDispatcher("/from.jsp").forward(request, response);
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
       // processRequest(request, response);
       // Update human details
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        int typeId = Integer.parseInt(request.getParameter("typeid"));

        Human human = new Human();
        human.setId(Integer.parseInt(id));
        human.setName(name);
        human.setDob(java.sql.Date.valueOf(dob));
        human.setGender(gender);
        
        HumanType type = new HumanType();
        type.setTypeId(typeId);
        human.setType(type);

        DBContext db = new DBContext();
        db.updateHuman(human);
        
        HttpSession session = request.getSession();
        session.setAttribute("message", "Human updated successfully!");
        
        response.sendRedirect("ListController");
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
