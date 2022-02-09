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
public class Student {
    
    private int emplid;
    private String name;
    private int dept;
    private int transfer;
    private String status;
    private int credits;
    private double gpa;
    
    public Student(int emplid, String name, int dept, int transfer, String status, int credits, double gpa){
        
        super();
        
        this.emplid = emplid;
        this.name = name;
//        this.firstName = firstName;
        this.dept = dept;
        this.transfer = transfer;
        this.status = status;
        this.credits = credits;
        this.gpa = gpa;
        
        
    }
    
    public int getEmplid(){
        return emplid;
    }
    
    public void setEmplid(int emplid){
        this.emplid = emplid;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
//    public String getFirstName(){
//        return firstName;
//    }
//    
//    public void setFirstName(String firstName){
//        this.firstName = firstName;
//    }
    
    public int getDept(){
        return dept;
    }
    
    public void setDept(int dept){
        this.dept = dept;
    }
            
    public int getTransfer(){
        return transfer;
    }
    
    public void setTransfer(int transfer){
        this.transfer = transfer;
    }
         
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
         
    public int getCredits(){
        return credits;
    }
    
    public void setCredits(int credits){
        this.credits = credits;
    }
         
    public double getGPA(){
        return gpa;
    }
    
    public void setGPA(double gpa){
        this.gpa = gpa;
    }
    
    @Override
    public String toString(){
        return String.format("STUDENT: EMPLID:%s, NAME: %s, DEPT: %s, TRANSFER: %s, STATUS: %s, CREDITS: %s, GPA:%s\n",
                emplid, name, dept, transfer, status, credits, gpa);
    }
            
    
    
  
    
    

    
}
