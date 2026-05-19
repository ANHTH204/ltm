/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Hex_CheckSum {

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("36.50.135.242", 2206);

            InputStream in =
                    socket.getInputStream();

            OutputStream out =
                    socket.getOutputStream();

            String request =
                    "B22DCVT036;at7XBqf8";

            out.write(request.getBytes());
            out.flush();

            byte[] buffer =
                    new byte[4096];

            int len =
                    in.read(buffer);

            String received =
                    new String(buffer, 0, len);

            System.out.println(received);

            String[] packets =
                    received.split("\\|");

            int validPackets = 0;

            int payloadBytes = 0;

            for (String packet : packets) {

            String[] hexBytes =
                    packet.split("-");

            if (hexBytes.length < 2) {
                continue;
            }

            int sum = 0;

            for (int i = 0; i < hexBytes.length - 1; i++) {

                int value =
                        Integer.parseInt(
                                hexBytes[i].trim(),
                                16
                        );

                sum += value;
            }

            sum %= 256;

            int checksum =
                    Integer.parseInt(
                            hexBytes[hexBytes.length - 1].trim(),
                            16
                    );

            if (sum == checksum) {

                validPackets++;

                payloadBytes +=
                        (hexBytes.length - 1);
            }
        }

            String result =
                    "valid="
                            + validPackets
                            + ";payloadBytes="
                            + payloadBytes;

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
