/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.studentdatabase;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Victor Nestor
 */
public class SchoolDAO extends javax.swing.JFrame {
    
    private Connection conn;
    
    public SchoolDAO() throws Exception {
        
        // Database properties
        String dburl = "jdbc:derby://localhost:1527/StudentDB";
        conn = DriverManager.getConnection(dburl);
        
        System.out.println("School DAO: DB Connection successful to: " + dburl);
        
    }
    
    public List<School> getAllSchools() throws Exception {
        List<School> list = new ArrayList<>();
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("SELECT * FROM SCHOOLS");
            
            while (rs.next()) {
                School tempSchool = convertRowToSchool(rs);
                list.add(tempSchool); 
            }
            
            return list;
        }
        
        finally {
            close(stmt, rs);
        }     
    }
    
    private School convertRowToSchool(ResultSet rs) throws SQLException {
        int code = rs.getInt("SCODE");
        String name = rs.getString("SNAME");
        String address = rs.getString("SADDRESS");
        
        School tempSchool = new School(code, name, address);
        
        return tempSchool;
    }
    
    private static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        
        if (rs != null) {
            rs.close();
        }
        
        if (stmt != null) {
            
        }
        
        if(conn != null) {
            conn.close();
        }
    }
    
    private void close(Statement stmt, ResultSet rs) throws SQLException {
        close(null, stmt, rs);
    }
    
    
}
    
