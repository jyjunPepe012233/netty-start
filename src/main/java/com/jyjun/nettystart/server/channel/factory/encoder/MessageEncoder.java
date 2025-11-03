package com.jyjun.nettystart.server.channel.factory.encoder;

import com.jyjun.nettystart.server.message.outbound.OutboundMessage;
import io.netty.buffer.ByteBuf;

public interface MessageEncoder<T extends OutboundMessage> {

    void encode(T in, ByteBuf out);

}
