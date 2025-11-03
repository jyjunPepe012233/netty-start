package com.jyjun.nettystart.server.channel.factory.decoder;

import com.jyjun.nettystart.server.message.inbound.InboundMessage;
import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DecoderDispatcher {

    private final Map<Integer, MessageDecoder<?>> decoders;

    public InboundMessage decode(int opcode, ByteBuf in) {
        var decoder = decoders.get(opcode);
        return decoder != null
                ? decoder.decode(in)
                : null;
    }

}
