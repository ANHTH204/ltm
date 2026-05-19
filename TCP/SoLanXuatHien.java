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
import java.io.*;
import java.util.*;
public class SoLanXuatHien {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("ptit.store",2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        String ma = "B22DCVt036;R2yrTuxg";
        out.write(ma);
        out.newLine();
        out.flush();
        
        String s = in.readLine().trim();
        int cnt[] = new int[256];
        for(char x : s.toCharArray()){
            cnt[x]++;
        }
        String res = "";
        for(char x : s.toCharArray()){
            if(cnt[x] > 1){
                res = res + x + ":" + cnt[x] + ",";
                cnt[x] = 0;
            }
        }
        out.write(res);
        out.newLine();
        out.flush();
        
    }
}
