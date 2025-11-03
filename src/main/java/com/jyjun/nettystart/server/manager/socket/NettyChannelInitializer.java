package com.jyjun.nettystart.server.manager.socket;

import com.jyjun.nettystart.server.channel.handler.ChannelProvider;
import com.jyjun.nettystart.server.channel.handler.NetworkMessageDecoder;
import com.jyjun.nettystart.server.channel.handler.NetworkMessageEncoder;
import com.jyjun.nettystart.server.channel.handler.ServiceDispatcher;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final ChannelProvider channelProvider;
    private final NetworkMessageDecoder networkMessageDecoder;
    private final ServiceDispatcher serviceDispatcher;
    private final NetworkMessageEncoder networkMessageEncoder;

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(channelProvider);
        pipeline.addLast(networkMessageDecoder);
        pipeline.addLast(serviceDispatcher);
        pipeline.addLast(networkMessageEncoder);
    }

}
