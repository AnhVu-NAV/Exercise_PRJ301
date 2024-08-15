/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
//import java.util.Date;
import model.Human;
import model.HumanType;

/**
 *
 * @author AnhVuNAV
 */
public class InsertController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        DBContext db = new DBContext();
        ArrayList<HumanType> types = db.getTypes();
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
        //processRequest(request, response);
        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String dobStr = request.getParameter("dob");
        String genderStr = request.getParameter("gender");
        String typeIdStr = request.getParameter("typeid");

//        // Chuyển đổi dữ liệu
//        Date dob = null;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date parsedDate = sdf.parse(dobStr);
//            dob = new Date(parsedDate.getTime()); // Chuyển đổi từ java.util.Date sang java.sql.Date
//        } catch (ParseException e) {
//            e.printStackTrace();
//            // Xử lý lỗi ở đây
//        }

        Date dob = Date.valueOf(dobStr);
        
        boolean gender = Boolean.parseBoolean(genderStr); // Chuyển đổi từ String sang boolean
        int typeId = Integer.parseInt(typeIdStr); // Chuyển đổi từ String sang int

        // Tạo đối tượng Human mới
        Human newHuman = new Human();
        newHuman.setName(name);
        newHuman.setDob(dob);
        newHuman.setGender(gender);

        // Tạo đối tượng HumanType mới và gán vào Human
        HumanType ht = new HumanType();
        ht.setTypeId(typeId);
        newHuman.setType(ht);

        // Lưu đối tượng Human vào cơ sở dữ liệu
        DBContext db = new DBContext();
        db.insertHuman(newHuman);
        
        HttpSession session = request.getSession();
        session.setAttribute("message", "Human added successfully!");

        // Chuyển hướng về trang danh sách
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
