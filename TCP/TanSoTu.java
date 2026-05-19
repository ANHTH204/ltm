/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */


import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class TanSoTu {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("ptit.store", 2208);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

     
            String request = "B22DCVT036;WuBQy5yq";

            bw.write(request);
            bw.newLine();
            bw.flush();

        
            String received = br.readLine();

            System.out.println(received);

       
            received = received.toLowerCase();

       
            received = received.replaceAll("[^a-z0-9 ]", " ");

        
            received = received.replaceAll("\\s+", " ").trim();

            System.out.println(received);

       
            TreeMap<String, Integer> map = new TreeMap<>();

            if (!received.isEmpty()) {

                String[] arr = received.split(" ");

                for (String word : arr) {

                    if (word.length() == 0) continue;

                    map.put(word,
                            map.getOrDefault(word, 0) + 1);
                }
            }

            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, Integer> e : map.entrySet()) {

                sb.append(e.getKey())
                        .append("=")
                        .append(e.getValue())
                        .append("|");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

            String result = sb.toString();

            System.out.println(result);

            bw.write(result);
            bw.newLine();
            bw.flush();

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}