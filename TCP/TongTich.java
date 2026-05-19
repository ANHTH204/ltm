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
public class TongTich {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("ptit.store",2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        String ma = "B22DCVT036;u6fVKdBZ";
        out.writeUTF(ma);
        out.flush();
        
        int a = in.readInt();
        int b = in.readInt();
        int sum = a + b;
        int tich = a*b;
        
        out.writeInt(sum);
        out.writeInt(tich);
        out.flush();
        
        socket.close();
    }
}
