package com.jyjun.nettystart.server.message.outbound;

import com.jyjun.nettystart.server.message.BaseMessage;

public interface OutboundMessage extends BaseMessage {

    int getPayloadLength();

}
