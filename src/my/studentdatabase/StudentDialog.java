package my.studentdatabase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField emplidTextField;
	private JTextField nameTextField;
	private JTextField deptnoTextField;
	private JTextField transferTextField;
	private JTextField statusTextField;
	private JTextField creditsTextField;
	private JTextField gpaTextField;
	
	private StudentDAO studentDAO;
	
	private StudentDatabaseUI studentDatabaseUI;
	
	private Student previousStudent = null;
	private boolean updateMode = false;
	
	// Constructor for when we call StudentDialog
	
	public StudentDialog(StudentDatabaseUI theStudentDatabaseUI, StudentDAO theStudentDAO, Student thePreviousStudent, boolean theUpdateMode) {
		
		this();
		
		studentDAO = theStudentDAO;
		studentDatabaseUI = theStudentDatabaseUI;
		
		previousStudent = thePreviousStudent;
		
		updateMode = theUpdateMode;
		
		if (updateMode) {
			setTitle("Update Student");
			emplidTextField.setEditable(false);
			populateGUI(previousStudent);
		}
		
	}
	
	private void populateGUI(Student theStudent) {
		
		emplidTextField.setText(String.valueOf(theStudent.getEmplid()));
		nameTextField.setText(theStudent.getName());
		deptnoTextField.setText(String.valueOf(theStudent.getDept()));
		transferTextField.setText(String.valueOf(theStudent.getTransfer()));
		statusTextField.setText(theStudent.getStatus());
		creditsTextField.setText(String.valueOf(theStudent.getCredits()));
		gpaTextField.setText(String.valueOf(theStudent.getGPA()));
		
	}
	
	public StudentDialog(StudentDatabaseUI theStudentDatabaseUI, StudentDAO theStudentDAO) {
		this(theStudentDatabaseUI, theStudentDAO, null, false);
	}

	/**
	 * Create the dialog.
	 */
	public StudentDialog() {
		setTitle("Add Student");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblEMPLID = new JLabel("EMPLID: ");
			contentPanel.add(lblEMPLID, "2, 2, right, bottom");
		}
		{
			emplidTextField = new JTextField();
			contentPanel.add(emplidTextField, "4, 2, fill, default");
			emplidTextField.setColumns(10);
		}
		{
			JLabel lblStatus = new JLabel("STATUS: ");
			contentPanel.add(lblStatus, "8, 2, right, default");
		}
		{
			statusTextField = new JTextField();
			contentPanel.add(statusTextField, "10, 2, fill, default");
			statusTextField.setColumns(10);
		}
		{
			JLabel lblFullName = new JLabel("FULL NAME: ");
			contentPanel.add(lblFullName, "2, 6, right, default");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "4, 6, fill, default");
			nameTextField.setColumns(10);
		}
		{
			JLabel lblCredits = new JLabel("CREDITS: ");
			contentPanel.add(lblCredits, "8, 6, right, default");
		}
		{
			creditsTextField = new JTextField();
			contentPanel.add(creditsTextField, "10, 6, fill, default");
			creditsTextField.setColumns(10);
		}
		{
			JLabel lblDeptNo = new JLabel("DEPT NO: ");
			contentPanel.add(lblDeptNo, "2, 10, right, default");
		}
		{
			deptnoTextField = new JTextField();
			contentPanel.add(deptnoTextField, "4, 10, fill, default");
			deptnoTextField.setColumns(10);
		}
		{
			JLabel lblGpa = new JLabel("GPA:");
			contentPanel.add(lblGpa, "8, 10, right, default");
		}
		{
			gpaTextField = new JTextField();
			contentPanel.add(gpaTextField, "10, 10, fill, default");
			gpaTextField.setColumns(10);
		}
		{
			JLabel lblTransfer = new JLabel("TRANSFER: ");
			contentPanel.add(lblTransfer, "2, 14, right, default");
		}
		{
			transferTextField = new JTextField();
			contentPanel.add(transferTextField, "4, 14, fill, default");
			transferTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Save the student
						saveStudent();
					}
				});
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveStudent() {
		// Get the student info from GUI
		int emplid = Integer.parseInt(emplidTextField.getText());
		String name = nameTextField.getText();
		int deptno = Integer.parseInt(deptnoTextField.getText());
		int transfer = Integer.parseInt(transferTextField.getText());
		String status = statusTextField.getText();
		int credits = Integer.parseInt(creditsTextField.getText());
		double gpa = Double.parseDouble(gpaTextField.getText());
		
		Student tempStudent = null;
		
		if(updateMode) {
			tempStudent = previousStudent;
			
			tempStudent.setName(name);
			tempStudent.setDept(deptno);
			tempStudent.setTransfer(transfer);
			tempStudent.setStatus(status);
			tempStudent.setCredits(credits);
			tempStudent.setGPA(gpa);
			
		} else {
			
			tempStudent = new Student(emplid, name, deptno, transfer, status, credits, gpa);
			
		}
		
		
		try {
			
			// Save to the Student Database
			if (updateMode) {
				
				studentDAO.updateStudent(tempStudent);
				
			} else {
				
				studentDAO.addStudent(tempStudent);	
				
			}
			
			// Close dialog
			setVisible(false);
			dispose();
			
			// Refresh GUI list
			studentDatabaseUI.refreshStudentsView();
			
			// Show success message
			JOptionPane.showMessageDialog(studentDatabaseUI, 
					"Student saved successfully!",
					"Student saved",
					JOptionPane.INFORMATION_MESSAGE);
			
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					studentDatabaseUI,
					"Error saving student: " + exc.getMessage(), "An error occured!", JOptionPane.ERROR_MESSAGE);
		}
			
		
	}

}
