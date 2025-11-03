package com.jyjun.nettystart.server.channel.factory.encoder.impl;

import com.jyjun.nettystart.server.channel.factory.encoder.MessageEncoder;
import com.jyjun.nettystart.server.message.outbound.impl.PlayerMoveBroadcastMessage;
import io.netty.buffer.ByteBuf;

public class PlayerMoveBroadcastMessageEncoder implements MessageEncoder<PlayerMoveBroadcastMessage> {

    @Override
    public void encode(PlayerMoveBroadcastMessage in, ByteBuf out) {
        out.writeShort(in.player());
        out.writeFloat(in.x());
        out.writeFloat(in.y());
        out.writeFloat(in.z());
    }

}
