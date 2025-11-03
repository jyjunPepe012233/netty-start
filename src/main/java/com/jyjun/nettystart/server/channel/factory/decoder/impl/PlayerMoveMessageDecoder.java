package com.jyjun.nettystart.server.channel.factory.decoder.impl;

import com.jyjun.nettystart.server.channel.factory.decoder.MessageDecoder;
import com.jyjun.nettystart.server.message.inbound.impl.PlayerMoveMessage;
import io.netty.buffer.ByteBuf;

public class PlayerMoveMessageDecoder implements MessageDecoder<PlayerMoveMessage> {

    @Override
    public PlayerMoveMessage decode(ByteBuf in) {
        int playerId = in.readUnsignedShort();
        float x = in.readFloat();
        float y = in.readFloat();
        float z = in.readFloat();
        return new PlayerMoveMessage(playerId, x, y, z);
    }

}
