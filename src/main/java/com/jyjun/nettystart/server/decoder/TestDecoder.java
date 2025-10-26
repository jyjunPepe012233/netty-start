package com.jyjun.nettystart.server.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class TestDecoder extends ByteToMessageDecoder {

    private int DATA_LENGTH = 2048;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < DATA_LENGTH) {
            return;
        }
        out.add(in.readBytes(DATA_LENGTH));
    }

}
