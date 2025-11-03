package com.jyjun.nettystart.server.channel.factory.decoder;

import com.jyjun.nettystart.server.message.inbound.InboundMessage;
import io.netty.buffer.ByteBuf;

public interface MessageDecoder<T extends InboundMessage> {

    T decode(ByteBuf in);

}
