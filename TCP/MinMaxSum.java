/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class MinMaxSum {

    public static void main(String[] args) {

        String serverHost = "ptit.store";
        int serverPort = 2207;

        String studentCode = "B22DCVT036";
        String qCode = "iUJxbsDx"; 

        try {
            // Kết nối server
            Socket socket = new Socket(serverHost, serverPort);

            DataInputStream dis =
                    new DataInputStream(socket.getInputStream());

            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());

            String sendData = studentCode + ";" + qCode;

            dos.writeUTF(sendData);
            dos.flush();

            int n = dis.readInt();

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            long sum = 0;

            System.out.println("n = " + n);

            for (int i = 0; i < n; i++) {

                int value = dis.readInt();

                System.out.print(value + " ");

                if (value < min) {
                    min = value;
                }

                if (value > max) {
                    max = value;
                }

                sum += value;
            }

            System.out.println();

    
            String result = min + ";" + max + ";" + sum;

            System.out.println("Result: " + result);

            dos.writeUTF(result);
            dos.flush();

       
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
