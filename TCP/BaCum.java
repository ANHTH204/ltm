/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.*;

public class BaCum {

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
                    "B22DCVT036;vzyDmdxd";

            out.write(request);
            out.newLine();
            out.flush();

  
            String received =
                    in.readLine();

            System.out.println(received);

    
            received = received.toLowerCase();

            received =
                    received.replaceAll(
                            "[^a-z0-9 ]",
                            " "
                    );

            received =
                    received.replaceAll(
                            "\\s+",
                            " "
                    ).trim();

            String[] words =
                    received.split(" ");

   
            Map<String, Integer> freq =
                    new HashMap<>();

            for (int i = 0; i < words.length - 1; i++) {

                String bigram =
                        words[i] + "_"
                                + words[i + 1];

                freq.put(
                        bigram,
                        freq.getOrDefault(bigram, 0) + 1
                );
            }

     
            List<Map.Entry<String, Integer>> list =
                    new ArrayList<>(freq.entrySet());

            list.sort((a, b) -> {

                if (!a.getValue().equals(b.getValue())) {

                    return b.getValue() - a.getValue();
                }

                return a.getKey().compareTo(b.getKey());
            });

 
            // lấy tối đa 3 cụm

            StringBuilder result =
                    new StringBuilder();

            int limit =
                    Math.min(3, list.size());

            for (int i = 0; i < limit; i++) {

                Map.Entry<String, Integer> e =
                        list.get(i);

                result.append(
                        e.getKey()
                );

                result.append("=");

                result.append(
                        e.getValue()
                );

                if (i < limit - 1) {

                    result.append("|");
                }
            }

  
            System.out.println(result);

            out.write(result.toString());
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
