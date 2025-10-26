package com.jyjun.nettystart.server.socket;

import com.jyjun.nettystart.server.decoder.TestDecoder;
import com.jyjun.nettystart.server.handler.TestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final TestHandler testHandler;

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        TestDecoder testDecoder = new TestDecoder();

        pipeline.addLast(testDecoder);
        pipeline.addLast(testHandler);
    }

}
