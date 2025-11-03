package com.jyjun.nettystart.server.channel.config;

import com.jyjun.nettystart.server.channel.factory.decoder.MessageDecoder;
import com.jyjun.nettystart.server.channel.factory.decoder.impl.PlayerMoveMessageDecoder;
import com.jyjun.nettystart.server.channel.factory.encoder.MessageEncoder;
import com.jyjun.nettystart.server.channel.factory.encoder.impl.PlayerMoveBroadcastMessageEncoder;
import com.jyjun.nettystart.server.channel.handler.ChannelProvider;
import com.jyjun.nettystart.server.channel.service.GameService;
import com.jyjun.nettystart.server.channel.service.impl.PlayerMoveService;
import com.jyjun.nettystart.server.protocol.OpcodeProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class NettyHandlerDispatcherConfig {

    private final ChannelProvider channelProvider;

    @Bean
    public Map<Integer, GameService<?>> services() {
        var service = new HashMap<Integer, GameService<?>>();
        service.put(OpcodeProtocol.PLAYER_MOVED.getOpcode(), new PlayerMoveService(channelProvider));
        return service;
    }

    @Bean
    public Map<Integer, MessageDecoder<?>> decoders() {
        var decoders = new HashMap<Integer, MessageDecoder<?>>();
        decoders.put(OpcodeProtocol.PLAYER_MOVED.getOpcode(), new PlayerMoveMessageDecoder());

        return decoders;
    }

    @Bean Map<Integer, MessageEncoder<?>> encoders() {
        var encoders = new HashMap<Integer, MessageEncoder<?>>();
        encoders.put(OpcodeProtocol.PLAYER_MOVED_BROADCAST.getOpcode(), new PlayerMoveBroadcastMessageEncoder());

        return encoders;
    }

}