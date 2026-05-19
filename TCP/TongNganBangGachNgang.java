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
import java.util.*;
import java.net.*;
public class TongNganBangGachNgang {
    public static void main(String[] args)throws Exception{
        Socket socket = new Socket("ptit.store",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        String ma = "B22DCVT036;wtuBBV56";
        out.write(ma.getBytes());
        out.flush();
        
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer,0,len);
        String[] so = s.trim().split("\\|");
        int sum = 0;
        
        for(String x : so){
            sum += Integer.parseInt(x);
        }
        
        out.write(String.valueOf(sum).getBytes());
        out.flush();
        
    }
}
