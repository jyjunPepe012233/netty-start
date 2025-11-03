package com.jyjun.nettystart.server.channel.factory.encoder;

import com.jyjun.nettystart.server.message.outbound.OutboundMessage;
import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EncoderDispatcher {

    private final Map<Integer, MessageEncoder<?>> encoders;

    public void encode(int opcode, OutboundMessage message, ByteBuf out) {
        var encoder = encoders.get(opcode);
        if (encoder != null) {
            invokeEncoder(encoder, message, out);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends OutboundMessage> void invokeEncoder(MessageEncoder<T> encoder, OutboundMessage message, ByteBuf out) {
        encoder.encode((T)message, out);
    }

}
