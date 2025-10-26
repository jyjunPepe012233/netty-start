package com.jyjun.nettystart.client.socket;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@AllArgsConstructor
public class NonSslSocket {

    private String host;

    private int port;

    public void run(int messageLength) {
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            socket.connect(socketAddress);

            ClientSocket clientSocket = new ClientSocket(socket);
            clientSocket.sendFixedLength(messageLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
