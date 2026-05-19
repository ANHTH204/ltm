/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.*;
import java.net.Socket;
import java.util.StringJoiner;

public class TCP_Object_Customer {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("36.50.135.242", 2209);

            ObjectOutputStream out =
                    new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            ObjectInputStream in =
                    new ObjectInputStream(socket.getInputStream());

 
            String request = "B22DCVT036;n4SR41IF";

            out.writeObject(request);
            out.flush();

            Customer customer = (Customer) in.readObject();

            System.out.println(customer.getName());

         
            String rawName = customer.getName().trim().toLowerCase();

            String[] words = rawName.split("\\s+");


            String lastName =
                    words[words.length - 1].toUpperCase();

     
            StringJoiner sj = new StringJoiner(" ");

            for (int i = 0; i < words.length - 1; i++) {

                String w = words[i];

                sj.add(
                        Character.toUpperCase(w.charAt(0))
                                + w.substring(1)
                );
            }

            String newName =
                    lastName + ", " + sj.toString();

            customer.setName(newName);

            String dob = customer.getDayOfBirth();

            String[] dateParts = dob.split("-");

            String newDob =
                    dateParts[1] + "/"
                            + dateParts[0] + "/"
                            + dateParts[2];

            customer.setDayOfBirth(newDob);

            StringBuilder username = new StringBuilder();

            for (int i = 0; i < words.length - 1; i++) {
                username.append(words[i].charAt(0));
            }

            username.append(words[words.length - 1]);

            customer.setUserName(username.toString());

            out.writeObject(customer);
            out.flush();

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
