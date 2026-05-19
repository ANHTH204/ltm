/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object_Employee_1 {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress =
                    InetAddress.getByName("36.50.135.242");

            int serverPort = 2209;

  
            String request = ";B22DCVT036;FneUXPoD";

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

            Employee employee =
                    (Employee) ois.readObject();

            String rawName =
                    employee.getName().trim().toLowerCase();

            String[] words = rawName.split("\\s+");

            StringBuilder normalizedName =
                    new StringBuilder();

            for (String word : words) {

                normalizedName.append(
                        Character.toUpperCase(word.charAt(0))
                );

                normalizedName.append(word.substring(1));

                normalizedName.append(" ");
            }

            employee.setName(
                    normalizedName.toString().trim()
            );

            double newSalary =
                    employee.getSalary() * 1.08;

    
            BigDecimal bd =
                    new BigDecimal(newSalary)
                            .setScale(2, RoundingMode.HALF_UP);

            employee.setSalary(bd.doubleValue());

            ByteArrayOutputStream bos =
                    new ByteArrayOutputStream();

            ObjectOutputStream oos =
                    new ObjectOutputStream(bos);

            oos.writeObject(employee);
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