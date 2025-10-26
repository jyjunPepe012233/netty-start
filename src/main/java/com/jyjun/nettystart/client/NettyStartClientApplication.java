package com.jyjun.nettystart.client;

import com.jyjun.nettystart.client.socket.NonSslSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

public class NettyStartClientApplication {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 9000;
        try {
            System.out.println("Enter message length: ");
            Scanner sc = new Scanner(System.in);
            int messageLength = Integer.parseInt(sc.nextLine());

            NonSslSocket socket = new NonSslSocket(host, port);
            socket.run(messageLength);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
