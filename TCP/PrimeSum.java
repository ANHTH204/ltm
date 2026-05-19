/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PrimeSum {

    public static boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {

            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("36.50.135.242", 2206);

            InputStream in =
                    socket.getInputStream();

            OutputStream out =
                    socket.getOutputStream();

            String request =
                    "B22DCVT036;oDFWDHyv";

            out.write(request.getBytes());
            out.flush();

            byte[] buffer =
                    new byte[4096];

            int len =
                    in.read(buffer);

            String received =
                    new String(buffer, 0, len);

            System.out.println(received);

            String[] nums =
                    received.split(",");

            long primeSum = 0;

            int xor = 0;

            for (int i = 0; i < nums.length; i++) {

                int value =
                        Integer.parseInt(nums[i].trim());

                // index bắt đầu từ 1
                int position = i + 1;

                if (isPrime(position)) {

                    primeSum += value;
                }

                xor ^= value;
            }

            primeSum %= 65536;

            String result =
                    "primeSum="
                            + primeSum
                            + ";xor="
                            + xor;

            System.out.println(result);

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
