package TCP;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TCP_NIO_1 {
    public static void main(String[] args) throws Exception {

        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("ptit.store", 2211));

        // a. gửi code (theo frame)
        String code = "B22DCVT036;QILgg5Cm";
        writeFrame(channel, code);

        // b. nhận 3 frame
        StringBuilder fullLog = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String part = readFrame(channel);
            fullLog.append(part);
            
            if (!part.endsWith(";")) {
                fullLog.append(";");
            }
        }
        

        // c. parse key=value
        Map<String, String> map = new HashMap<>();
        String[] pairs = fullLog.toString().split(";");

        for (String p : pairs) {
            if (p.contains("=")) {
                String[] kv = p.split("=", 2);
                map.put(kv[0], kv[1]);
            }
        }

        String result = "ip=" + map.getOrDefault("ip", "") +
                        ";path=" + map.getOrDefault("path", "") +
                        ";status=" + map.getOrDefault("status", "");

        // gửi lại
        writeFrame(channel, result);

        channel.close();
    }

    // ===================== READ FRAME =====================
    static String readFrame(SocketChannel channel) throws Exception {

        // đọc 4 byte length
        ByteBuffer lenBuf = ByteBuffer.allocate(4);
        readFully(channel, lenBuf);
        lenBuf.flip();
        int len = lenBuf.getInt();

        // đọc payload
        ByteBuffer dataBuf = ByteBuffer.allocate(len);
        readFully(channel, dataBuf);
        dataBuf.flip();

        return StandardCharsets.UTF_8.decode(dataBuf).toString();
    }

    // ===================== WRITE FRAME =====================
    static void writeFrame(SocketChannel channel, String msg) throws Exception {

        byte[] data = msg.getBytes(StandardCharsets.UTF_8);

        ByteBuffer buf = ByteBuffer.allocate(4 + data.length);
        buf.putInt(data.length);
        buf.put(data);
        buf.flip();

        while (buf.hasRemaining()) {
            channel.write(buf);
        }
    }

    // ===================== READ FULLY =====================
    static void readFully(SocketChannel channel, ByteBuffer buf) throws Exception {
        while (buf.hasRemaining()) {
            int r = channel.read(buf);
            if (r == -1) throw new RuntimeException("Connection closed");
        }
    }
}