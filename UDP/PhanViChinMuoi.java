/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class PhanViChinMuoi {

    public static void main(String[] args) {

        try {

            DatagramSocket socket =
                    new DatagramSocket();

            InetAddress serverAddress =
                    InetAddress.getByName("36.50.135.242");

            int serverPort = 2207;

            String request =
                    ";B22DCVT036;NtzUccfZ";

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

            double sum = 0;

            for (int i = 0; i < n; i++) {

                arr[i] =
                        Integer.parseInt(nums[i].trim());

                sum += arr[i];
            }

            double avg =
                    sum / n;

            int aboveAvg = 0;

            for (int value : arr) {

                if (value > avg) {

                    aboveAvg++;
                }
            }

   
            Arrays.sort(arr);

            int index =
                    (int) Math.ceil(n * 0.9) - 1;

            int p90 =
                    arr[index];

            String result =
                    requestId
                            + ";p90="
                            + p90
                            + ";aboveAvg="
                            + aboveAvg;

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