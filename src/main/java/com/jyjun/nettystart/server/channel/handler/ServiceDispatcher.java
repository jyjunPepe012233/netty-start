package com.jyjun.nettystart.server.channel.handler;

import com.jyjun.nettystart.server.channel.service.GameService;
import com.jyjun.nettystart.server.message.BaseMessage;
import com.jyjun.nettystart.server.message.inbound.InboundMessage;
import io.netty.channel.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
public class ServiceDispatcher extends SimpleChannelInboundHandler<BaseMessage> {

    private final Map<Integer, GameService<?>> services;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage in) {
        var service = services.get(in.getOpcode());
        if (service != null) {
            invokeService(service, ctx, in);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends InboundMessage> void invokeService(GameService<T> service, ChannelHandlerContext ctx, BaseMessage message) {
        service.provide(ctx, (T)message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        cause.printStackTrace();
    }

}