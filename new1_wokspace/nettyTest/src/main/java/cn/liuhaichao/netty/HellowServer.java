package cn.liuhaichao.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HellowServer {
	public static void main(String[] args) throws InterruptedException {
		//定义一对线程组
		//主线程组
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		//从线程组
		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(parentGroup, childGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new HelloServerInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
			channelFuture.channel().closeFuture().sync();
		} finally{
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}
}
