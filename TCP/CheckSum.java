/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CheckSum {

    public static void main(String[] args) {

        String serverHost = "ptit.store";
        int serverPort = 2206;

        String studentCode = "B22DCVT036";
        String qCode = "6LRCo6kP"; 

        try {
          
            Socket socket = new Socket(serverHost, serverPort);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

       
            String sendData = studentCode + ";" + qCode;

            out.write(sendData.getBytes());
            out.flush();

           
            byte[] buffer = new byte[1024];

            int len = in.read(buffer);

            String received = new String(buffer, 0, len);

            System.out.println("Received: " + received);

           
            String[] numbers = received.split(",");

            int sum = 0;

            for (String num : numbers) {
                sum += Integer.parseInt(num.trim());
            }

            int checksum = sum % 256;

            System.out.println("Checksum: " + checksum);

         
            String result = String.valueOf(checksum);

            out.write(result.getBytes());
            out.flush();

          
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
