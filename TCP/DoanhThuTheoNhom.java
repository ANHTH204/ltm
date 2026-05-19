/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DoanhThuTheoNhom {

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("36.50.135.242", 2207);

            DataInputStream in =
                    new DataInputStream(
                            socket.getInputStream()
                    );

            DataOutputStream out =
                    new DataOutputStream(
                            socket.getOutputStream()
                    );

            String request =
                    "B22DCVT036;J9YdlEQk";

            out.writeUTF(request);
            out.flush();

            int n = in.readInt();

            double totalRevenue = 0;

            Map<String, Double> categoryRevenue =
                    new HashMap<>();

            for (int i = 0; i < n; i++) {

                String category =
                        in.readUTF();

                double amount =
                        in.readDouble();

                int quantity =
                        in.readInt();

                double revenue =
                        amount * quantity;

                totalRevenue += revenue;

                categoryRevenue.put(
                        category,
                        categoryRevenue.getOrDefault(category, 0.0)
                                + revenue
                );
            }

            String bestCategory = "";

            double maxRevenue = -1;

            for (String category : categoryRevenue.keySet()) {

                double revenue =
                        categoryRevenue.get(category);

                if (revenue > maxRevenue) {

                    maxRevenue = revenue;
                    bestCategory = category;

                } else if (revenue == maxRevenue) {

                    if (category.compareTo(bestCategory) < 0) {

                        bestCategory = category;
                    }
                }
            }

            BigDecimal bd =
                    new BigDecimal(totalRevenue)
                            .setScale(2, RoundingMode.HALF_UP);

            totalRevenue = bd.doubleValue();

         
            String result =
                    "category="
                            + bestCategory
                            + ";total="
                            + String.format("%.2f", totalRevenue)
                            + ";records="
                            + n;

            System.out.println(result);

            out.writeUTF(result);
            out.flush();

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
