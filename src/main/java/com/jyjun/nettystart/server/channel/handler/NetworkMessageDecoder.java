package com.jyjun.nettystart.server.channel.handler;

import com.jyjun.nettystart.server.channel.factory.decoder.DecoderDispatcher;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NetworkMessageDecoder extends ByteToMessageDecoder {

    private static final int MAGIC_LENGTH = 2;

    private static final int PAYLOAD_LEN_LENGTH = 2;

    private static final int OPCODE_LENGTH = 2;

    private static final int HEADER_LENGTH = MAGIC_LENGTH
            + PAYLOAD_LEN_LENGTH
            + OPCODE_LENGTH;

    private final DecoderDispatcher decoderDispatcher;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < HEADER_LENGTH) {
            return;
        }

        short magicByte = in.readShort();
        if (magicByte != 0x3D3D) {
            return;
        }

        int payloadLength = in.readUnsignedShort();
        if (in.readableBytes() < HEADER_LENGTH + payloadLength) {
            return;
        }

        int opcode = in.readUnsignedByte();
        var message = decoderDispatcher.decode(opcode, in);
        out.add(message);
    }

}
