/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author Muhamed S. NasR
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");

        // received Data
        //String pass = request.getParameter("Pass");
        //String user = request.getParameter("user");

        try {

            // DB connection 

            Connection con = null;
            PreparedStatement stat = null;

            String user2 = "student1";
            String pass2 = "1993";
            String sql = "select * from CUSTOMER";
            //static String sql ="update employee set sex = 'female' where sex ='femal'";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            ResultSet rs = null;

            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection(url, user2, pass2);
            stat = con.prepareStatement(sql);
            rs = stat.executeQuery();


            /*    
             String user1 = "fuck";
             String pass1 = "fuck";
             String url = "jdbc:derby://localhost:1527/fuck";    //derby URL
             ResultSet rs = null;
            
             Class.forName("org.apache.derby.jdbc.ClientDriver");  //derby DB

             Connection con = DriverManager.getConnection(url, user1, pass1);
             String sql = "select * from CUSTOMER ";

             //select * from CUSTOMER where user_name like '"+user+"' and password like '"+pass+"'
            
             Statement stm = con.prepareCall(sql);

             //PreparedStatement st = con.prepareStatement(sql);

             //rs = st.executeQuery(sql);
            
            
             rs = stm.executeQuery(sql);
            
            
             */


            if (con != null) {



                System.out.println("logged in");
            } else {

                System.out.println("null");
            }


            boolean b = rs.next();
            System.out.println("RS NEXT:" + b);


            if (rs.next()) {

                // if true that means login data is true 
                // then we go to the dispatcher stage 

                System.out.println("rs ok");
                //Customer c = new Customer();
                // c.setName(rs.getString("name"));

                //c.setId(rs.getInt("id"));

                // request.getSession().setAttribute("cast", c);

                RequestDispatcher v = request.getRequestDispatcher("Welcome");
                v.forward(request, response);
                if (v == null) {

                    System.out.println("V is NULL");
                }
                
            



            } else {
                System.out.println("rs not ok");
           }


        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    





    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
