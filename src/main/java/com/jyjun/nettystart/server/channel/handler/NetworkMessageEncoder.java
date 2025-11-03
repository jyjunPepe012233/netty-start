package com.jyjun.nettystart.server.channel.handler;

import com.jyjun.nettystart.server.channel.factory.encoder.EncoderDispatcher;
import com.jyjun.nettystart.server.message.BaseMessage;
import com.jyjun.nettystart.server.message.outbound.OutboundMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
public class NetworkMessageEncoder extends MessageToMessageEncoder<OutboundMessage> {

    private final EncoderDispatcher encoderDispatcher;

    @Value("${network.protocol.magic}")
    private short magic;

    @Override
    protected void encode(ChannelHandlerContext ctx, OutboundMessage in, List<Object> out) {
        ByteBuf buf = ctx.alloc().buffer();

        buf.writeShort(magic);

        int payloadLength = in.getPayloadLength();
        buf.writeShort(payloadLength);

        int opcode = in.getOpcode();
        buf.writeShort(opcode);

        encoderDispatcher.encode(opcode, in, buf);
    }

}
