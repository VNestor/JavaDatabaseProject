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
    private JPanel panel_1;
    private JButton btnAddStudent;
    private JButton btnUpdateStudent;
    private JButton btnDeleteStudent;
    

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
            JOptionPane.showMessageDialog(this, "Error: " + exc, "An error occured!", JOptionPane.ERROR_MESSAGE);
        }
        
        setTitle("Student Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,850,450);
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
                    
                    if (emplid >= 111111 && emplid <= 999999) {
                            students = studentDAO.searchStudents(emplid);
                    } else if (emplid == 0 ) {
                        students = studentDAO.getAllStudents();
                    } else {
                        JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error: Please enter valid EMPLID or 0 to show all students.", 
                    		"An error occured!", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    // Create the model and update the "table"
                    StudentTableModel model = new StudentTableModel(students);
                    
                    table.setModel(model);
                    
                    
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error: Please enter valid EMPLID or 0 to show all students.", 
                    		"An error occured!", JOptionPane.ERROR_MESSAGE);
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
                    
                    schools = schoolDAO.getAllSchools();
                    
                    // Create the model and update the "table"
                    SchoolTableModel model = new SchoolTableModel(schools);
                    
                    table.setModel(model);
                    
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error: " + exc, "An error occured!", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error: " + exc, "An error occured!", JOptionPane.ERROR_MESSAGE);
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
        
        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        
        btnAddStudent = new JButton("Add Student");
        btnAddStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// Create Dialog
        		StudentDialog dialog = new StudentDialog(StudentDatabaseUI.this, studentDAO);
        		
        		// Show Dialog
        		dialog.setVisible(true);
        	}
        });
        panel_1.add(btnAddStudent);
        
        btnUpdateStudent = new JButton("Update Student");
        btnUpdateStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Get the selected student
        		int row = table.getSelectedRow();
        		
        		// Make sure a row is selected
        		if (row < 0) {
        			JOptionPane.showMessageDialog(StudentDatabaseUI.this, "You must select a student!",
        					"An error occured!", JOptionPane.ERROR_MESSAGE);
        			return;
        			
        		}
        		
        		// Get the current student
        		Student tempStudent = (Student) table.getValueAt(row, StudentTableModel.OBJECT_COL);
        		
        		// Create dialog
        		StudentDialog dialog = new StudentDialog(StudentDatabaseUI.this, studentDAO, tempStudent, true);
        		
        		// Show dialog
        		dialog.setVisible(true);
        		
        	}
        });
        panel_1.add(btnUpdateStudent);
        
        btnDeleteStudent = new JButton("Delete Student");
        btnDeleteStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			
        			// Get the selected row
        			int row = table.getSelectedRow();
        			
        			// Make sure a row is selected
        			if (row < 0) {
        				JOptionPane.showMessageDialog(StudentDatabaseUI.this, 
        						"You must select a student!", "An error occured!", JOptionPane.ERROR_MESSAGE);
        				return;
        				
        			}
        			
        			// Prompt the user
        			int response = JOptionPane.showConfirmDialog(
        					StudentDatabaseUI.this, "Are you sure you want to delete this student?", "Confirm Student Deletion",
        					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        			
        			if (response != JOptionPane.YES_OPTION) {
        				return;
        			}
        			
        			// Get the current student
        			Student tempStudent = (Student) table.getValueAt(row, StudentTableModel.OBJECT_COL);
        			
        			// Delete the student
        			studentDAO.deleteStudent(tempStudent.getEmplid());
        			
        			// Refresh GUI
        			refreshStudentsView();
        			
        			// Show success message 
        			JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Student deleted successfully!", "Student Deleted",
        					JOptionPane.INFORMATION_MESSAGE);
        			
        		} catch (Exception exc) {
        			JOptionPane.showMessageDialog(StudentDatabaseUI.this, "Error deleting employee: " + exc.getMessage(), 
        					"An error occured!", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        panel_1.add(btnDeleteStudent);
        
        
        
        
    }
    
    public void refreshStudentsView() {
		try {
			
			List<Student> students = studentDAO.getAllStudents();
			
			// Create the model and update the "table".
			StudentTableModel model = new StudentTableModel(students);
			
			table.setModel(model);
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "An error occured!", JOptionPane.ERROR_MESSAGE);
		}
		
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
