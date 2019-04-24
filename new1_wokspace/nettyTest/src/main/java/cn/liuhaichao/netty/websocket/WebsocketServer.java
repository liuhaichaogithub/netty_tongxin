package cn.liuhaichao.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebsocketServer {
		public static void main(String[] args) throws InterruptedException {
			NioEventLoopGroup mainGroup = new NioEventLoopGroup();
			NioEventLoopGroup subGroup = new NioEventLoopGroup();
			try {
				ServerBootstrap server = new ServerBootstrap();
				server.group(mainGroup,subGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WebsocketInitialiaer());
				ChannelFuture channelFuture = server.bind(8088).sync();
				channelFuture.channel().closeFuture().sync();
			} finally {
				mainGroup.shutdownGracefully();
				subGroup.shutdownGracefully();
			}
		}
}
