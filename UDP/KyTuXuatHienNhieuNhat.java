/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KyTuXuatHienNhieuNhat {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("ptit.store");
        int port = 2208;
        
        String code = ";B22DCVT036;BnWE0WtM";
        DatagramPacket sendPacket = new DatagramPacket(code.getBytes(),code.length(),serverAddress, port);
        socket.send(sendPacket);
        
        byte[] buf = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
        socket.receive(receivePacket);
        
        String s = new String(receivePacket.getData(),0 , receivePacket.getLength()).trim();
        System.out.println(s);
        String[] mang = s.trim().split(";");
        String reqId  = mang[0];
        String chuoi = mang[1];
        
        Map<Character,List<Integer>> map = new HashMap<>();
        for (int i = 0 ; i < chuoi.length();i++){
            char c = chuoi.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i + 1);
        }
        
        char maxChar = chuoi.charAt(0);
        int maxCount = map.get(maxChar).size();
        
        for(int i = 0; i < chuoi.length();i++){
            char c = chuoi.charAt(i);
            int size = map.get(c).size();
            
            if(size > maxCount){
                maxCount = size;
                maxChar = c;
            }
        }
        
        StringBuilder positions = new StringBuilder();
        for(int pos: map.get(maxChar)){
            positions.append(pos).append(",");
        }
        
        String res = reqId + ";" + maxChar + ":" + positions.toString();
        DatagramPacket sendPacket1 = new DatagramPacket(res.getBytes(),res.length(), serverAddress, port);
        socket.send(sendPacket1);
        
        socket.close();
        
    }
}
