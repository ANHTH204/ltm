
package TCP;

import java.io.*;
import java.net.*;

public class TCP_Object_Laptop {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("ptit.store", 2209);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        // 1. gửi mã
        String code = "B22DCVT036;34IqG9DP";
        out.writeObject(code);
        out.flush();

        // 2. nhận object Laptop
        Laptop laptop = (Laptop) in.readObject();

        // ===== sửa name =====
        String[] words = laptop.getName().trim().split("\\s+");
        
        if(words.length > 1){
            String temp = words[0];
            words[0] = words[words.length -1];
            words[words.length -1] = temp;
        }
        laptop.setName(String.join(" ", words));
        

        // ===== sửa quantity =====
        String q = String.valueOf(laptop.getQuantity());
        int fixedQuantity = Integer.parseInt(
                new StringBuilder(q).reverse().toString()
        );

        // set lại
        laptop.setQuantity(fixedQuantity);

        // 3. gửi lại object
        out.writeObject(laptop);
        out.flush();

        // 4. đóng
        socket.close();
    }
}