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

public class DemBit {

    public static void main(String[] args) {

        String serverHost = "ptit.store";
        int serverPort = 2206;

        String studentCode = "B22DCVT036";
        String qCode = "GgWK7N73"; 

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


            int ones = 0;
            int zeros = 0;

            for (char c : received.toCharArray()) {

                if (c == '1') {
                    ones++;
                } else if (c == '0') {
                    zeros++;
                }
            }


            String result = "ones=" + ones + ";zeros=" + zeros;

            System.out.println("Result: " + result);

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
