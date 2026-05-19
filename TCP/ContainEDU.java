/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class ContainEDU {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("ptit.store",2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        String ma = "B22DCVT036;SgdzEHEn";
        out.write(ma);
        out.newLine();
        out.flush();
        
        String s = in.readLine();
        String[] mien = s.trim().split(",");
        StringBuilder sb = new StringBuilder();
        
        for(String x : mien){
            x.trim();
            if(x.endsWith(".edu")){
                if(sb.length() > 0) sb.append(",");
                sb.append(x);
            }
        }
        out.write(sb.toString());
        out.newLine();
        out.flush();
    }
}
