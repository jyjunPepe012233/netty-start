package com.jyjun.nettystart.server.protocol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OpcodeProtocol {

    PLAYER_MOVED(0),
    PLAYER_MOVED_BROADCAST(1),
    ;

    private final int opcode;

    public int getOpcode() {
        return opcode;
    }

}
