/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */
import java.io.*;
import java.net.Socket;


public class TCP_Object_Student {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2209);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        String mes = "B22DCVT036;qoSCfaed";
        out.writeObject(mes);
        out.flush();
        
        Student student = (Student) in.readObject();
        student.setCode(student.getCode().toUpperCase());

        float gpa = student.getGpa();
        String letter;
        
        if (gpa >= 3.6f) { 
            letter = "A";
        } else if (gpa >= 3.2f ) { letter = "B";}
        else if(gpa >= 2.5f ) {letter = "C";}
        else if (gpa >= 2.0f) {letter = "D";}
        else {letter = "F";}
        
        student.setGpaLetter(letter);
        out.writeObject(student);
        out.flush();
        socket.close();
    }
}
