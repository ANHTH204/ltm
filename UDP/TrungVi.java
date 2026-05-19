/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class TrungVi {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress =
                    InetAddress.getByName("36.50.135.242");

            int serverPort = 2207;

            String request =
                    ";B22DCVT036;nYzUtQgg";

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

            String received =
                    new String(
                            receivePacket.getData(),
                            0,
                            receivePacket.getLength()
                    );

            System.out.println(received);

    
            String[] parts =
                    received.split(";", 2);

            String requestId = parts[0];

            String data = parts[1];

     
            String[] nums =
                    data.split(",");

            int[] arr =
                    new int[nums.length];

            for (int i = 0; i < nums.length; i++) {

                arr[i] =
                        Integer.parseInt(nums[i].trim());
            }

            Arrays.sort(arr);

            int n = arr.length;

            String medianResult;

            if (n % 2 == 1) {

                medianResult =
                        String.valueOf(arr[n / 2]);

            } else {

                double median =
                        (arr[n / 2 - 1] + arr[n / 2]) / 2.0;

                BigDecimal bd =
                        new BigDecimal(median)
                                .setScale(2, RoundingMode.HALF_UP);

                medianResult =
                        bd.toString();
            }

            String result =
                    requestId + ";" + medianResult;

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
