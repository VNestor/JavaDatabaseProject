/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.studentdatabase;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.border.EmptyBorder;



/**
 *
 * @author Victor Nestor
 */
public class StudentDatabaseUI extends javax.swing.JFrame {
    
    private JPanel contentPane;
    private JTextField emplidTextField;
    private JButton btnSearch;
    private JButton btnShowSchools;
    private JButton btnShowDepartments;
    private JScrollPane scrollPane;
    private JTable table;
    
    private StudentDAO studentDAO;
    private SchoolDAO schoolDAO;
    private DepartmentDAO departmentDAO;
    

    /**
     * Creates new form StudentDatabaseUI
     */
    public StudentDatabaseUI() {
        initComponents();
        
        // Create the DAO
        
        try {
            
            studentDAO = new StudentDAO();
            schoolDAO = new SchoolDAO();
            departmentDAO = new DepartmentDAO();
            
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "\nError: ", JOptionPane.ERROR_MESSAGE);
        }
        
        setTitle("Student Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,650,300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        
        JLabel labelEnterEMPLID = new JLabel("Enter EMPLID");
        panel.add(labelEnterEMPLID);
        
        emplidTextField = new JTextField();
        panel.add(emplidTextField);
        emplidTextField.setColumns(10);
        
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Get EMPLID from text field.
                // Call DAO and get corresponding student.
                // If emplid is empty, get all students.
                // Print out students
                
                try {
                    
                    
                    int emplid = Integer.parseInt(emplidTextField.getText());
                    
                    List<Student> students = null;
                    
                    if (emplid != 0 && emplid >= 111111 && emplid <= 999999) {
                            students = studentDAO.searchStudents(emplid);
                    } else {
                        students = studentDAO.getAllStudents();
                    }
                    
                    // Create the model and update the "table"
                    StudentTableModel model = new StudentTableModel(students);
                    
                    table.setModel(model);
                    
                    
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error: Please enter valid EMPLID or 0 to show all students.", "\nError: ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnShowSchools = new JButton("Show All Schools");
        btnShowSchools.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Call School DAO and get all schools
                // Print out schools
                
                try {
                    
                    List<School> schools = null;
                    System.out.println("List made");
                    
                    System.out.println("Getting schools");
                    schools = schoolDAO.getAllSchools();
                    System.out.println("Got schools");
                    
                    // Create the model and update the "table"
                    SchoolTableModel model = new SchoolTableModel(schools);
                    System.out.println("Model created");
                    
                    table.setModel(model);
                    System.out.println("Model set");
                    
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error: " + exc, "\nError: ", JOptionPane.ERROR_MESSAGE);
                } 
            }
            
        });
        
        btnShowDepartments = new JButton("Show All Departments");
        btnShowDepartments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Call Department DAO and get all departments
                // Print out departments
                
                try {
                    
                    List<Department> departments = null;
                    
                    departments = departmentDAO.getAllDepartments();
                    
                    // Create the model and update the "table"
                    DepartmentTableModel model = new DepartmentTableModel(departments);
                    
                    table.setModel(model);
                    
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error: " + exc, "\nError: ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(btnSearch);
        panel.add(btnShowSchools);
        panel.add(btnShowDepartments);
        
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentDatabaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDatabaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDatabaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDatabaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StudentDatabaseUI().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
