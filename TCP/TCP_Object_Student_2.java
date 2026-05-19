/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCP_Object_Student_2 {

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("36.50.135.242", 2209);

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            socket.getOutputStream()
                    );

            out.flush();

            ObjectInputStream in =
                    new ObjectInputStream(
                            socket.getInputStream()
                    );

            String request =
                    "B22DCVT036;HvgGBaGT";

            out.writeObject(request);
            out.flush();

            Student student =
                    (Student) in.readObject();

            String code =
                    student.getCode().toUpperCase();

    
            code = code.replaceAll("[^A-Z0-9]", "");

            student.setCode(code);

            double gpa = student.getGpa();

            String gpaLetter;

            if (gpa >= 3.7) {
                gpaLetter = "A";
            } else if (gpa >= 3.4) {
                gpaLetter = "B+";
            } else if (gpa >= 3.0) {
                gpaLetter = "B";
            } else if (gpa >= 2.5) {
                gpaLetter = "C+";
            } else if (gpa >= 2.0) {
                gpaLetter = "C";
            } else {
                gpaLetter = "F";
            }

            student.setGpaLetter(gpaLetter);

            out.writeObject(student);
            out.flush();

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
