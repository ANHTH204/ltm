/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author Admin
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
public class MaCEASER {
        public static String decryptCaesar(String encrypted, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : encrypted.toCharArray()) {

   
            if (c >= 'A' && c <= 'Z') {
                char decoded = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                result.append(decoded);
            }

            else if (c >= 'a' && c <= 'z') {
                char decoded = (char) ((c - 'a' - shift + 26) % 26 + 'a');
                result.append(decoded);
            }

        
            else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String serverHost = "ptit.store";
        int serverPort = 2207;

        String studentCode = "B22DCVT036";
        String qCode = "PuEqdSls"; 

        try {
            
            Socket socket = new Socket(serverHost, serverPort);

           
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            
            String message = studentCode + ";" + qCode;
            dos.writeUTF(message);
            dos.flush();

            String encrypted = dis.readUTF();
            int shift = dis.readInt();

            System.out.println("Encrypted message: " + encrypted);
            System.out.println("Shift: " + shift);

           
            String decrypted = decryptCaesar(encrypted, shift);

            System.out.println("Decrypted message: " + decrypted);

         
            dos.writeUTF(decrypted);
            dos.flush();

            
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
