/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.*;
import java.net.*;

public class UDP_Object_Product {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress =
                    InetAddress.getByName("36.50.135.242");

            int serverPort = 2209;


            String request = ";B22DCVT036;rrpW61Zc";

            byte[] sendData = request.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(
                            sendData,
                            sendData.length,
                            serverAddress,
                            serverPort
                    );

            socket.send(sendPacket);


            byte[] buffer = new byte[4096];

            DatagramPacket receivePacket =
                    new DatagramPacket(buffer, buffer.length);

            socket.receive(receivePacket);

            byte[] receivedData = receivePacket.getData();

   
            String requestId =
                    new String(receivedData, 0, 8);

            ByteArrayInputStream bis =
                    new ByteArrayInputStream(
                            receivedData,
                            8,
                            receivePacket.getLength() - 8
                    );

            ObjectInputStream ois =
                    new ObjectInputStream(bis);

            Product product =
                    (Product) ois.readObject();

  
            String[] words = product.getName().trim().split("\\s+");

            if (words.length >= 2) {

                String temp = words[0];
                words[0] = words[words.length - 1];
                words[words.length - 1] = temp;
            }

            String fixedName = String.join(" ", words);

            product.setName(fixedName.toString());

            int quantity = product.getQuantity();

            int reversed = 0;

            while (quantity > 0) {

                reversed = reversed * 10 + quantity % 10;

                quantity /= 10;
            }

            product.setQuantity(reversed);

  
            ByteArrayOutputStream bos =
                    new ByteArrayOutputStream();

            ObjectOutputStream oos =
                    new ObjectOutputStream(bos);

            oos.writeObject(product);
            oos.flush();

            byte[] objectBytes = bos.toByteArray();

    
            ByteArrayOutputStream finalBos =
                    new ByteArrayOutputStream();

            finalBos.write(requestId.getBytes());

            finalBos.write(objectBytes);

            byte[] finalData = finalBos.toByteArray();

         
            DatagramPacket resultPacket =
                    new DatagramPacket(
                            finalData,
                            finalData.length,
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