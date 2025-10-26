package com.jyjun.nettystart.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
public class TestHandler extends ChannelInboundHandlerAdapter {

    private int DATA_LENGTH = 2048;

    private ByteBuf buffer;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buffer = ctx.alloc().buffer(DATA_LENGTH);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buffer = null;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        var remoteAddress = ctx.channel().remoteAddress().toString();
        log.info("[TestHandler] channelActive(): Remote Address: " + remoteAddress);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        var messageBuf = (ByteBuf)msg;
        buffer.writeBytes(messageBuf);
        messageBuf.release();

        final ChannelFuture future = ctx.writeAndFlush(buffer);
        future.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}