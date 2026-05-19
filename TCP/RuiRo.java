/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RuiRo {

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("36.50.135.242", 2208);

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()
                            )
                    );

            BufferedWriter out =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream()
                            )
                    );

    
            String request =
                    "B22DCVT036;YjY4B98g";

            out.write(request);
            out.newLine();
            out.flush();

   
            String received =
                    in.readLine();

            System.out.println(received);

            String[] logs =
                    received.split("\\|\\|");

            List<String> userIds =
                    new ArrayList<>();

            double totalAmount = 0;

            for (String log : logs) {

                String[] parts =
                        log.trim().split("\\s+");

                String userId = parts[0];

                int risk = 0;

                double amount = 0;

                for (String part : parts) {

                    if (part.startsWith("risk=")) {

                        risk = Integer.parseInt(
                                part.substring(5)
                        );
                    }

                    else if (part.startsWith("amount=")) {

                        amount = Double.parseDouble(
                                part.substring(7)
                        );
                    }
                }

              
                if (risk >= 70 || amount >= 5000) {

                    userIds.add(userId);

                    totalAmount += amount;
                }
            }

         
            BigDecimal bd =
                    new BigDecimal(totalAmount)
                            .setScale(2, RoundingMode.HALF_UP);

            totalAmount = bd.doubleValue();

       
            String ids =
                    String.join(",", userIds);

            String result =
                    "count="
                            + userIds.size()
                            + ";ids="
                            + ids
                            + ";amount="
                            + String.format("%.2f", totalAmount);

            System.out.println(result);

            out.write(result);
            out.newLine();
            out.flush();

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
