/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TangLienTiepDaiNhat{

    public static void main(String[] args) {

        try {

            DatagramSocket socket =
                    new DatagramSocket();

            InetAddress serverAddress =
                    InetAddress.getByName("36.50.135.242");

            int serverPort = 2207;

         
            String request =
                    ";B22DCVT036;fBGMEZOK";

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

   
            String[] nums =
                    data.split(",");

            int n = nums.length;

            int[] arr =
                    new int[n];

            for (int i = 0; i < n; i++) {

                arr[i] =
                        Integer.parseInt(nums[i].trim());
            }

         
            int bestStart = 0;
            int bestLen = 1;

            int currentStart = 0;
            int currentLen = 1;

            for (int i = 1; i < n; i++) {

                if (arr[i] > arr[i - 1]) {

                    currentLen++;

                } else {

                    if (currentLen > bestLen) {

                        bestLen = currentLen;
                        bestStart = currentStart;
                    }

                    currentStart = i;
                    currentLen = 1;
                }
            }

       
            if (currentLen > bestLen) {

                bestLen = currentLen;
                bestStart = currentStart;
            }

            StringBuilder segment =
                    new StringBuilder();

            int sum = 0;

            for (int i = bestStart;
                 i < bestStart + bestLen;
                 i++) {

                segment.append(arr[i]);

                sum += arr[i];

                if (i < bestStart + bestLen - 1) {

                    segment.append(",");
                }
            }

            String result =
                    requestId
                            + ";segment="
                            + segment
                            + ";length="
                            + bestLen
                            + ";sum="
                            + sum;

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
