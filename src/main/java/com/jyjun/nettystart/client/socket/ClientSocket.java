package com.jyjun.nettystart.client.socket;

import lombok.AllArgsConstructor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

@AllArgsConstructor
public class ClientSocket {

    private Socket socket;

    public void sendFixedLength(int messageLength) {
        int delimiterLength = 256;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < messageLength; i++) {
            stringBuilder.append("A");
        }
        byte[] totalData = stringBuilder.toString().getBytes();

        System.out.println("Sending message");
        try {
            OutputStream oStream = socket.getOutputStream();

            for (int i = 0; i < messageLength / delimiterLength; i++) {
                byte[] sending =
                        Arrays.copyOfRange(
                                totalData,
                                i * delimiterLength,
                                (i + 1) * delimiterLength
                        );
                System.out.println("sending..." + (i + 1));
                oStream.write(sending);
                oStream.flush();
                Thread.sleep(500);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Receiving message");
        try {
            InputStream iStream = socket.getInputStream();
            DataInputStream diStream = new DataInputStream(iStream);

            byte[] reply = new byte[messageLength];
            diStream.readFully(reply);
            System.out.println(new String(reply));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
