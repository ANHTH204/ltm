/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;

public class TCP_Object_Product_1 {

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("36.50.135.242", 2209);

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            socket.getOutputStream()
                    );

            out.flush();

            ObjectInputStream in =
                    new ObjectInputStream(
                            socket.getInputStream()
                    );

            String request =
                    "B22DCVT036;Dc0cBCvL";

            out.writeObject(request);
            out.flush();

            Product product =
                    (Product) in.readObject();

            double newPrice =
                    product.getPrice()
                            * (100.0 - product.getDiscount())
                            / 100.0;

            BigDecimal bd =
                    new BigDecimal(newPrice)
                            .setScale(2, RoundingMode.HALF_UP);

            product.setPrice(bd.doubleValue());

            out.writeObject(product);
            out.flush();

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
