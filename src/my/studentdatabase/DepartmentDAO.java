/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.studentdatabase;


import java.util.*;
import java.sql.*;

/**
 *
 * @author VIctor Nestor
 */
public class DepartmentDAO extends javax.swing.JFrame {
    
    private Connection conn;
    
    public DepartmentDAO() throws Exception {
        
        // Database properties
        String dburl = "jdbc:derby://localhost:1527/StudentDB";
        conn = DriverManager.getConnection(dburl);
        
        System.out.println("Department DAO: DB Connection successful to: " + dburl);
    }
    
    public List<Department> getAllDepartments() throws Exception {
        List<Department> list = new ArrayList<>();
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM DEPARTMENT");
            
            while (rs.next()) {
                Department tempDepartment = convertRowToDepartment(rs);
                list.add(tempDepartment);
            }
            
            return list;
        }
        
        finally {
            close(stmt, rs);
        }
    }
    
    private Department convertRowToDepartment(ResultSet rs) throws SQLException {
        int deptno = rs.getInt("DEPTNO");
        String major = rs.getString("MAJOR");
        String degree = rs.getString("DEGREE");
        
        Department tempDepartment = new Department(deptno, major, degree);
        
        return tempDepartment;
    }
    
    private static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        
        if (rs != null) {
            rs.close();
        }
        
        if(stmt != null) {
            
        }
        
        if(conn != null) {
            conn.close();
        }
    }
    
    private void close(Statement stmt, ResultSet rs) throws SQLException {
        close(null, stmt, rs);
    }
}
