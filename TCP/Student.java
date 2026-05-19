/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */
import java.io.Serializable;
public class Student implements Serializable{
    private static final long serialVersionUID = 20151107;
    private int id;
    private String code;
    private float gpa;
    private String gpaLetter;
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}
    
    public float getGpa() {return gpa;}
    public void setGpa(float gpa) {this.gpa = gpa;}
    
    public String getGpaLetter() {return gpaLetter;}
    public void setGpaLetter(String gpaLetter) {this.gpaLetter = gpaLetter;}
    
}
