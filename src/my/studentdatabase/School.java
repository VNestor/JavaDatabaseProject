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
public class School {
    private int code;
    private String name;
    private String address;
    
    public School(int code, String name, String address){
        
        super();
        
        this.code = code;
        this.name = name;
        this.address = address;
        
    }
    
    public int getCode(){
        return code;
    }
    
    public void setCode(int code){
        this.code = code;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(){
        this.name = name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String addresss){
        this.address = address;
    }
    
    @Override
    public String toString(){
        return String.format("SCHOOL: NAME: %s, SCHOOL CODE: %s, ADDRESS: %s", name, code, address);
                
    }
}
