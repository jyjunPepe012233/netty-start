package com.jyjun.nettystart.server.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
@RequiredArgsConstructor
public class NettyServerSocket {

    private final ServerBootstrap serverBootstrap;

    private final InetSocketAddress tcpPort;

    private Channel serverChannel;

    public void start() {
        try {
            ChannelFuture serverChannelFuture = serverBootstrap.bind(tcpPort).sync();
            serverChannel = serverChannelFuture.channel().closeFuture().sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void stop() {
        if (serverChannel != null) {
            serverChannel.close();
            serverChannel.parent().closeFuture();
        }
    }


}
