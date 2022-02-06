/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.studentdatabase;


import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Victor Nestor
 */
class DepartmentTableModel extends AbstractTableModel {
    
    private static final int DEPTNO = 0;
    private static final int MAJOR = 1;
    private static final int DEGREE = 2;
    
    private String[] columnNames = {"DEPTNO", "MAJOR", "DEGREE"};
    
    private List<Department> departments;
    
    public DepartmentTableModel(List<Department> theDepartments) {
        departments = theDepartments;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return departments.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Department tempDepartment = departments.get(row);
        
        switch (col) {
            case DEPTNO:
                return tempDepartment.getDept();
            case MAJOR:
                return tempDepartment.getMajor();
            case DEGREE:
                return tempDepartment.getDegree();
            default:
                return tempDepartment.getDept();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }
    
}
