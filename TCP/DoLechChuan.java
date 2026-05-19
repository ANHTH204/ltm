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
import java.util.Arrays;

public class DoLechChuan {

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
                    "B22DCVT036;7aLzqrnh";

            out.writeUTF(request);
            out.flush();

   
            int n = in.readInt();

            double[] arr =
                    new double[n];

            double sum = 0;

            for (int i = 0; i < n; i++) {

                arr[i] = in.readDouble();

                sum += arr[i];
            }

       
            double[] sorted =
                    arr.clone();

            Arrays.sort(sorted);

            double trimmedSum = 0;

            for (int i = 1; i < n - 1; i++) {

                trimmedSum += sorted[i];
            }

            double trimmedAvg =
                    trimmedSum / (n - 2);

            double avg =
                    sum / n;

            double variance = 0;

            for (double value : arr) {

                variance +=
                        Math.pow(value - avg, 2);
            }

            variance /= n;

            double stddev =
                    Math.sqrt(variance);

        
            int outliers = 0;

            double threshold =
                    avg + stddev;

            for (double value : arr) {

                if (value > threshold) {

                    outliers++;
                }
            }

        
            BigDecimal bd1 =
                    new BigDecimal(trimmedAvg)
                            .setScale(2, RoundingMode.HALF_UP);

            BigDecimal bd2 =
                    new BigDecimal(stddev)
                            .setScale(2, RoundingMode.HALF_UP);

            trimmedAvg = bd1.doubleValue();
            stddev = bd2.doubleValue();

            
            String result =
                    "trimmedAvg="
                            + String.format("%.2f", trimmedAvg)
                            + ";stddev="
                            + String.format("%.2f", stddev)
                            + ";outliers="
                            + outliers;

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