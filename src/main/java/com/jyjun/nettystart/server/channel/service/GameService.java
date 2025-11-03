package com.jyjun.nettystart.server.channel.service;

import com.jyjun.nettystart.server.message.inbound.InboundMessage;
import io.netty.channel.ChannelHandlerContext;

public interface GameService<T extends InboundMessage> {

    void provide(ChannelHandlerContext ctx, T message);

}
