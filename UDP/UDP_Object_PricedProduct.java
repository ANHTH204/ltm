/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.*;

public class UDP_Object_PricedProduct {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress =
                    InetAddress.getByName("36.50.135.242");

            int serverPort = 2209;

            String request = ";B22DCVT036;qP9PP1y3";

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

            PricedProduct product =
                    (PricedProduct) ois.readObject();

            double finalPrice =
                    product.getBasePrice()
                            * (1 + product.getTaxRate() / 100.0)
                            * (1 - product.getDiscountRate() / 100.0);

            BigDecimal bd =
                    new BigDecimal(finalPrice)
                            .setScale(2, RoundingMode.HALF_UP);

            product.setFinalPrice(bd.doubleValue());

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
