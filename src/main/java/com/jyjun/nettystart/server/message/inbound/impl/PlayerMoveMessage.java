package com.jyjun.nettystart.server.message.inbound.impl;

import com.jyjun.nettystart.server.message.inbound.InboundMessage;
import com.jyjun.nettystart.server.protocol.OpcodeProtocol;

public record PlayerMoveMessage(
        int player,
        float x,
        float y,
        float z
) implements InboundMessage {

    @Override
    public int getOpcode() {
        return OpcodeProtocol.PLAYER_MOVED.getOpcode();
    }

}
