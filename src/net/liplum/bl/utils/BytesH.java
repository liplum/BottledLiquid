package net.liplum.bl.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static java.lang.Math.max;

public class BytesH {
    public static byte[] readBytes(InputStream stream) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream(max(8 * 1024, stream.available()));
            copyTo(stream, buffer, 8 * 1024);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long copyTo(InputStream input, OutputStream out, int bufferSize) throws IOException {
        long bytesCopied = 0;
        byte[] buffer = new byte[bufferSize];
        int bytes = input.read(buffer);
        while (bytes >= 0) {
            out.write(buffer, 0, bytes);
            bytesCopied += bytes;
            bytes = input.read(buffer);
        }
        return bytesCopied;
    }
}
