/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;



/* ĐỂ bài:
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng
2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau:    
Đối tượng trao đổi là thể hiện của lớp UDP.Book được mô tả:        
Tên đầy đủ lớp: UDP.Book      
Các thuộc tính: id (String), title (String), author (String), isbn (String), publishDate (String)      
Hàm khởi tạo:          public Book(String id, String title, String author, String isbn, String publishDate)      
Trường dữ liệu: private static final long serialVersionUID = 20251107L    
Thực hiện:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode"
Ví dụ: ";B23DCCN005;eQkvAeId
b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Book từ server.
Trong đó, các thuộc tính id, title, author, isbn, và publishDate đã được thiết lập sẵn.
c. Thực hiện:          
Chuẩn hóa title: viết hoa chữ cái đầu của mỗi từ.          
Chuẩn hóa author theo định dạng "HỌ, Tên".          
Chuẩn hóa mã ISBN theo định dạng "978-3-16-148410-0"          
Chuyển đổi publishDate từ yyyy-mm-dd sang mm/yyyy.
d. Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Book đã được sửa đổi.    
Đóng socket và kết thúc chương trình.

*/
import java.io.*;
import java.net.*;
import java.util.*;

public class Object_1_Book {

    public static void main(String[] args) {
        String serverHost = "ptit.store";
        int port = 2209;

        String studentCode = "B22DCVT036";
        String qCode = "6GdG8Uu8";

        try {
            DatagramSocket socket = new DatagramSocket();

           
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendData = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName(serverHost);
            socket.send(new DatagramPacket(sendData, sendData.length, serverAddress, port));

          
            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            byte[] data = packet.getData();
            int length = packet.getLength();

         
            byte[] requestIdBytes = Arrays.copyOfRange(data, 0, 8);

         
            byte[] objectBytes = Arrays.copyOfRange(data, 8, length);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(objectBytes));
            Book book = (Book) ois.readObject();

           
            // title
            book.setTitle(capitalizeWords(book.getTitle()));

            // author
            book.setAuthor(formatAuthor(book.getAuthor()));

            // isbn
            book.setIsbn(formatISBN(book.getIsbn()));

            // date
            book.setPublishDate(formatDate(book.getPublishDate()));

            // ====== serialize lại ======
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(book);
            oos.flush();

            byte[] newObjectBytes = bos.toByteArray();

         
            ByteArrayOutputStream finalBos = new ByteArrayOutputStream();
            finalBos.write(requestIdBytes);
            finalBos.write(newObjectBytes);

            byte[] finalData = finalBos.toByteArray();

            socket.send(new DatagramPacket(finalData, finalData.length, serverAddress, port));

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== HELPER =====

    // Title: viết hoa chữ cái đầu mỗi từ
    public static String capitalizeWords(String str) {
        String[] words = str.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1))
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

  
    public static String formatAuthor(String author) {
        String[] parts = author.trim().split("\\s+");
        if (parts.length == 0) return author;

        String lastName = parts[0].toUpperCase(); 

        StringBuilder firstName = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            firstName.append(capitalizeWords(parts[i])).append(" ");
        }

        return lastName + ", " + firstName.toString().trim();
    }

    
    public static String formatISBN(String isbn) {
        String digits = isbn.replaceAll("[^0-9]", "");

        if (digits.length() != 13) return isbn;

        return digits.substring(0, 3) + "-" +
               digits.substring(3, 4) + "-" +
               digits.substring(4, 6) + "-" +
               digits.substring(6, 12) + "-" +
               digits.substring(12);
    }

   
    public static String formatDate(String date) {
        try {
            String[] parts = date.split("-");
            return parts[1] + "/" + parts[0];
        } catch (Exception e) {
            return date;
        }
    }
}