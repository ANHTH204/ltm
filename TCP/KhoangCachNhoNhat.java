/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */
import java.net.*;
import java.util.*;
import java.io.*;
public class KhoangCachNhoNhat {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("ptit.store",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        String ma = "B22DCVt036;3jEFwij5";
        out.write(ma.getBytes());
        out.flush();
        
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer,0,len);
        String[] parts = s.trim().split(",");
        
        int[] numbers = new int[parts.length];
        for(int i = 0; i < parts.length; i++){
            numbers[i] = Integer.parseInt(parts[i].trim());
        }
        
        Arrays.sort(numbers);
        int minDistance = Integer.MAX_VALUE;
        int num1 = 0;
        int num2 = 0;
        
        for(int i = 0; i < parts.length - 1; i++){
            int dist = numbers[i+1] - numbers[i];
            if (dist <= minDistance){
                minDistance = dist;
                num1 = numbers[i];
                num2 = numbers[i+1];
            }
        }
        
        String res = minDistance + "," + num1 + "," + num2;
        out.write(res.getBytes());
        out.flush();
    }
}
