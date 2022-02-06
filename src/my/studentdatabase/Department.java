/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.studentdatabase;

/**
 *
 * @author Victor Nestor
 */
public class Department {
    
    private int dept;
    private String major;
    private String degree;
    
    public Department(int dept, String major, String degree){
        
        super();
        
        this.dept = dept;
        this.major = major;
        this.degree = degree;
    }
    
    public int getDept(){
        return dept;
    }
    
    public void setDept(int dept){
        this.dept = dept;
    }
    
    public String getMajor(){
        return major;
    }
    
    public void setMajor(String Major){
        this.major = major;
    }
    
    public String getDegree(){
        return degree;
    }
    
    public void setDegree(String degree){
        this.degree = degree;
    }
    
    @Override
    public String toString() {
        return String.format("DEPARTMENT: DEPARTMENT CODE: %s, MAJOR: %s, DEGREE: %s", dept, major, degree);
    }
    
}
