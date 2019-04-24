package com.tongxin.netty;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


@Component
public class WebsocketServer {
	
	private static class SingletionWebsocketServer{
		static final WebsocketServer instance = new WebsocketServer();
	}
	
	public static WebsocketServer getInstance() {
		return SingletionWebsocketServer.instance;
		
	}
	
	private NioEventLoopGroup mainGroup;
	private NioEventLoopGroup subGroup;
	private ServerBootstrap server;
	private ChannelFuture channelFuture;
	
	public WebsocketServer() {
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		server = new ServerBootstrap();
		server.group(mainGroup, subGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WSServerInitialzer());
	}
	
	public void start() {
		this.channelFuture = server.bind(8088);
		System.err.println("netty websocket server 启动完毕");
	}
	
}
