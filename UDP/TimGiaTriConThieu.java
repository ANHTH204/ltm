/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

/**
 *
 * @author Admin
 */
import java.net.*;
import java.io.*;
import java.util.*;
public class TimGiaTriConThieu {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("ptit.store");
        int port = 2207;
        
        String ma = ";B22DCVT036;4oq4thAR";
        DatagramPacket sendPacket = new DatagramPacket(ma.getBytes(),ma.length(),serverAddress, port);
        socket.send(sendPacket);
        
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer,buffer.length);
        socket.receive(receivePacket);
        String s = new String(receivePacket.getData(),0,receivePacket.getLength());
        String[] parts = s.trim().split(";");
        String reqID = parts[0];
        int n = Integer.parseInt(parts[1]);
        
        boolean[] seen = new boolean[n + 1];
        if(parts.length > 2 && !parts[2].trim().isEmpty()){
            String[] nums = parts[2].split(",");
            for(String x : nums){
                int val = Integer.parseInt(x.trim());
                if(val >=1 && val <=n){
                    seen[val] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            if(!seen[i]){
                sb.append(i).append(",");
            }
        }
        if(sb.length() >0){
            sb.deleteCharAt(sb.length() - 1);
        }
        
        String output = reqID + ";" + sb.toString();
        DatagramPacket sendPacket1 = new DatagramPacket(output.getBytes(),output.length(),serverAddress,port);
        socket.send(sendPacket1);
        socket.close();
    }
}
