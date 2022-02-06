/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.studentdatabase;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Victor Nestor
 */
class StudentTableModel extends AbstractTableModel {
    
    private static final int EMPLID = 0;
    private static final int NAME = 1;
    private static final int DEPTNO = 2;
    private static final int TRANSFER = 3;
    private static final int STATUS = 4;
    private static final int CREDITS = 5;
    private static final int GPA = 6;
    
    private String[] columnNames = {"EMPLID", "NAME", "DEPTNO", "TRANSFER", "STATUS", "CREDITS", "GPA"};
    
    private List<Student> students;
    
    public StudentTableModel(List<Student> theStudents){
        students = theStudents;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return students.size();
    }
    
    @Override
    public String getColumnName(int col) {
       return columnNames[col];   
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        
        Student tempStudent = students.get(row);
        
        switch (col) {
         
            case EMPLID:
                return tempStudent.getEmplid();
            case NAME:
                return tempStudent.getName();
            case DEPTNO:
                return tempStudent.getDept();
            case TRANSFER:
                return tempStudent.getTransfer();
            case STATUS:
                return tempStudent.getStatus();
            case CREDITS:
                return tempStudent.getCredits();
            case GPA:
                return tempStudent.getGPA();
            default:
                return tempStudent.getEmplid();
        }
    }  
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }
}
