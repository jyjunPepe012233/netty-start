package com.jyjun.nettystart.server.channel.service.impl;

import com.jyjun.nettystart.server.channel.handler.ChannelProvider;
import com.jyjun.nettystart.server.channel.service.GameService;
import com.jyjun.nettystart.server.message.inbound.impl.PlayerMoveMessage;
import com.jyjun.nettystart.server.message.outbound.impl.PlayerMoveBroadcastMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerMoveService implements GameService<PlayerMoveMessage> {

    private final ChannelProvider channelProvider;

    @Override
    public void provide(ChannelHandlerContext ctx, PlayerMoveMessage message) {
        PlayerMoveBroadcastMessage playerMoveBroadcastMessage =
                PlayerMoveBroadcastMessage.of(message);

        ChannelGroup allPlayers =  channelProvider.getChannels();
        allPlayers.stream()
                .filter(ch -> ch != ctx.channel())
                .forEach(ch -> ch.writeAndFlush(playerMoveBroadcastMessage));
    }

}
