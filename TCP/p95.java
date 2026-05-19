/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class p95 {

    public static void main(String[] args) {

        String serverHost = "ptit.store";
        int serverPort = 2207;

        String studentCode = "B22DCVT036";
        String qCode = "Hdo88pFn"; // 

        try {
            // Kết nối server
            Socket socket = new Socket(serverHost, serverPort);

            DataInputStream dis =
                    new DataInputStream(socket.getInputStream());

            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());

            String sendData = studentCode + ";" + qCode;

            dos.writeUTF(sendData);
            dos.flush();

            int n = dis.readInt();

            double[] arr = new double[n];

            double sum = 0;

            for (int i = 0; i < n; i++) {

                arr[i] = dis.readDouble();

                System.out.println(arr[i]);

                sum += arr[i];
            }

            double average = sum / n;

            Arrays.sort(arr);

            int p95Index = (int) Math.ceil(n * 0.95) - 1;

            double p95 = arr[p95Index];


            int aboveAvg = 0;

            for (double value : arr) {
                if (value > average) {
                    aboveAvg++;
                }
            }

 
            String result = String.format(
                    "%.2f;%.2f;%d",
                    average,
                    p95,
                    aboveAvg
            );

            System.out.println("Result: " + result);

            dos.writeUTF(result);
            dos.flush();

            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
