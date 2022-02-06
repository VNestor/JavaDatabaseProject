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
class SchoolTableModel extends AbstractTableModel {
    
    private static final int SCODE = 0;
    private static final int SNAME = 1;
    private static final int SADDRESS = 2;
    
    private String[] columnNames = {"SCODE", "SNAME", "SADDRESS"};
    
    private List<School> schools;
    
    public SchoolTableModel(List<School> theSchools){
        schools = theSchools;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return schools.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        
        School tempSchool = schools.get(row);
        
        switch (col) {
            
            case SCODE:
                return tempSchool.getCode();
            case SNAME:
                return tempSchool.getName();
            case SADDRESS:
                return tempSchool.getAddress();
            default:
                return tempSchool.getCode();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }
    
}
