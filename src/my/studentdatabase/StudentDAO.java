/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.studentdatabase;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Victor Nestor
 */
public class StudentDAO extends javax.swing.JFrame {
    
    private Connection conn;
    
    public StudentDAO() throws Exception {
        
        // Database properties
        String dburl = "jdbc:derby://localhost:1527/StudentDB";
        conn = DriverManager.getConnection(dburl);
        
        System.out.println("Student DAO: DB connection successful to: " + dburl);   
    }
    
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM STUDENTS");
            
            while (rs.next()) {
                Student tempStudent = convertRowToStudent(rs);
                list.add(tempStudent);
            }
            
            return list;
        }
        
        finally {
            close(stmt,rs);     
        }
    }
    
    public List<Student> searchStudents(int emplid) throws Exception {
        List<Student> list = new ArrayList<>();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            stmt = conn.prepareStatement("SELECT * FROM STUDENTS WHERE EMPLID = ?");
            
            stmt.setInt(1, emplid);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Student tempStudent = convertRowToStudent(rs);
                list.add(tempStudent);
            }
            
            return list;
        }
        finally {
            close(stmt, rs);
        }   
    }
    
    public void updateStudent(Student theStudent) throws SQLException {
    	PreparedStatement stmt = null;
    	
    	try {
    		stmt = conn.prepareStatement("UPDATE STUDENTS SET "
    	
    				+ "NAME = ?, DEPTNO = ?, TRANSFER = ?, STATUS = ?, CREDITS = ?, GPA = ? " 
    				+ "WHERE EMPLID = ?");
    		
    		// Set params
            stmt.setString(1, theStudent.getName());
            stmt.setInt(2, theStudent.getDept());
            stmt.setInt(3, theStudent.getTransfer());
            stmt.setString(4, theStudent.getStatus());
            stmt.setInt(5, theStudent.getCredits());
            stmt.setDouble(6, theStudent.getGPA());
            stmt.setInt(7, theStudent.getEmplid());
            
            // Execute SQL
            stmt.executeUpdate();
    	} 
    	finally {
    		close(stmt);
    	}   	
    }
    
    public void deleteStudent(int emplid) throws SQLException {
    	PreparedStatement stmt = null;
    	
    	try {
    		// Prepare statement
    		stmt = conn.prepareStatement("DELETE FROM STUDENTS WHERE EMPLID = ?");
    		
    		// Set param
    		stmt.setInt(1,  emplid);
    		
    		// Execute SQL
    		stmt.executeUpdate();
    	}
    	finally {
    		close(stmt);
    	}
    }
    
    private Student convertRowToStudent(ResultSet rs) throws SQLException {
        int emplid = rs.getInt("EMPLID");
        String name = rs.getString("NAME");
//        String firstName = rs.getString("first_name");
        int dept = rs.getInt("DEPTNO");
        int transfer = rs.getInt("TRANSFER");
        String status = rs.getString("STATUS");
        int credits = rs.getInt("CREDITS");
        double gpa = rs.getDouble("GPA");
        
        Student tempStudent = new Student(emplid, name, dept, transfer, status, credits, gpa);
        
        return tempStudent;
        
    }
    
        
    public void addStudent(Student theStudent) throws Exception {
        PreparedStatement stmt = null;
        
        try {
            // Prepare the statement
            stmt = conn.prepareStatement("INSERT INTO STUDENTS "
                    + "(EMPLID, NAME, DEPTNO, TRANSFER, STATUS, CREDITS, GPA) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            
            // Set params
            stmt.setInt(1, theStudent.getEmplid());
            stmt.setString(2, theStudent.getName());
            stmt.setInt(3, theStudent.getDept());
            stmt.setInt(4, theStudent.getTransfer());
            stmt.setString(5, theStudent.getStatus());
            stmt.setInt(6, theStudent.getCredits());
            stmt.setDouble(7, theStudent.getGPA());
            
            // Execute SQL
            stmt.executeUpdate();
        }
        finally {
            close(stmt);
        }
        
    }
    
    private static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        
        if (rs != null) {
            rs.close();
        }
        
        if (stmt != null) {
            
        }
        
        if (conn != null) {
            conn.close();
        }
    }
    
    private void close(Statement stmt, ResultSet rs) throws SQLException {
        close(null, stmt, rs);
    }
    
    private void close(Statement stmt) throws SQLException {
    	close(null, stmt, null);
    }
    
//    public static void main(String[] args) throws Exception {
//        StudentDAO dao = new StudentDAO();
//        System.out.println(dao.searchStudents(596834));
//        
//        System.out.println(dao.getAllStudents());
//    }
    
}
