/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
d. Gửi datagram nộp kết quả theo định dạng requestId;answer. 
Ví dụ: 7Brz6QQA;refund-ticket-payment-payment-customer-payment-customer-ticket-refund-customer-shipping-shipping-payment.
*/
package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChuanHoaSlug {

    public static void main(String[] args) {

        try {

            DatagramSocket socket =
                    new DatagramSocket();

            InetAddress serverAddress =
                    InetAddress.getByName("36.50.135.242");

            int serverPort = 2208;

            String request =
                    ";B22DCVT036;LydUWvYu";

            byte[] sendData =
                    request.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(
                            sendData,
                            sendData.length,
                            serverAddress,
                            serverPort
                    );

            socket.send(sendPacket);

            byte[] buffer =
                    new byte[4096];

            DatagramPacket receivePacket =
                    new DatagramPacket(
                            buffer,
                            buffer.length
                    );

            socket.receive(receivePacket);

            String received =
                    new String(
                            receivePacket.getData(),
                            0,
                            receivePacket.getLength()
                    );

            System.out.println(received);

      
            String[] parts =
                    received.split(";", 2);

            String requestId =
                    parts[0];

            String data =
                    parts[1];


        
            data = data.toLowerCase();

        
            data = data.replaceAll("[^a-z0-9 ]", " ");

         
            data = data.replaceAll("\\s+", " ").trim();

      
            data = data.replace(" ", "-");

      
            String result =
                    requestId + ";" + data;

            System.out.println(result);

            byte[] resultData =
                    result.getBytes();

            DatagramPacket resultPacket =
                    new DatagramPacket(
                            resultData,
                            resultData.length,
                            serverAddress,
                            serverPort
                    );

            socket.send(resultPacket);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
