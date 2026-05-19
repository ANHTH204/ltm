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
import java.util.*;
import java.io.*;
public class LoaiBoDBVaTrung {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("ptit.store");
        int port = 2208;
        
        String code = ";B22DCVT036;eYlpOtv2";
        DatagramPacket sendPacket = new DatagramPacket(code.getBytes(),code.length(),serverAddress, port);
        socket.send(sendPacket);
        
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        
        String s = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println("Nhan: " + s);
        
        String[] parts = s.trim().split(";");
        String reqID = parts[0];
        String data = parts[1];
        
        Set<Character> seen = new HashSet<>();
        StringBuilder res = new StringBuilder();
        
        for (int  i = 0; i< data.length();i++){
            char c = data.charAt(i);
            if(Character.isLetter(c) && !seen.contains(c)){
                seen.add(c);
                res.append(c);
            }
        }
        
        String output = reqID + ";" + res.toString();
        DatagramPacket sendPacket1 = new DatagramPacket(output.getBytes(),output.length(),serverAddress, port);
        socket.send(sendPacket1);
        System.out.println("Send :" + output);
        socket.close();
}
}
