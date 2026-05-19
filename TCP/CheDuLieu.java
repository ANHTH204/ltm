/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CheDuLieu{

    public static void main(String[] args) {

        String serverHost = "ptit.store";
        int serverPort = 2208;

        String studentCode = "B22DCVT036";
        String qCode = "oqh8PKXh"; 

        try {
            // Kết nối server
            Socket socket = new Socket(serverHost, serverPort);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );


            String sendData = studentCode + ";" + qCode;

            bw.write(sendData);
            bw.newLine();
            bw.flush();

            String received = br.readLine();

            System.out.println("Received:");
            System.out.println(received);


            received = received.replaceAll(
                    "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
                    "[EMAIL]"
            );

            received = received.replaceAll(
                    "\\b0\\d{9}\\b",
                    "[PHONE]"
            );

            received = received.replaceAll(
                    "token=[^\\s|]+",
                    "token=[TOKEN]"
            );

            System.out.println("Processed:");
            System.out.println(received);

            bw.write(received);
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
