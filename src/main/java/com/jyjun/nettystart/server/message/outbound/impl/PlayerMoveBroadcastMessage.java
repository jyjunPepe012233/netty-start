package com.jyjun.nettystart.server.message.outbound.impl;

import com.jyjun.nettystart.server.message.inbound.impl.PlayerMoveMessage;
import com.jyjun.nettystart.server.message.outbound.OutboundMessage;

public record PlayerMoveBroadcastMessage(
        int player,
        float x,
        float y,
        float z
) implements OutboundMessage {

    @Override
    public int getPayloadLength() {
        return 8;
    }

    @Override
    public int getOpcode() {
        return 0;
    }

    public static PlayerMoveBroadcastMessage of(PlayerMoveMessage playerMoveMessage) {
        return new PlayerMoveBroadcastMessage(
                playerMoveMessage.player(),
                playerMoveMessage.x(),
                playerMoveMessage.y(),
                playerMoveMessage.z()
        );
    }

}
