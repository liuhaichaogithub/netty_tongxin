package com.tongxin.netty;

import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
/**
 * 
 * @author 82129
 *处理消息的handler
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	//TextWebSocketFrame 在netty中，用于websocket专门处理文本的对象，frame是消息载体
	//用于记录和管理所有客户端的channle
	private static ChannelGroup clients = 
			new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		//获取客户端传来的消息
		String text = msg.text();
		System.out.println("接收到的数据："+text);
		for (Channel channel : clients) {
			//将所有消息刷到客户端
//			channel.writeAndFlush(msg);
			channel.writeAndFlush
			(new TextWebSocketFrame("[服务器接收到消息时间：]"
			+
			LocalDateTime.now()+"消息为："+text));
		}
		/*//下面的方法和上面的fore循环作用一样
		clients.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息时间：]"
			+
			LocalDateTime.now()+"消息为："+msg));*/
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//客户端连接后，获取客户端的channel并且放到cannelGroup中管理
		clients.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//移除group中的channel自定的，可省略
		System.out.println("客户端断开，channel对应的长id"+ctx.channel().id().asLongText());
		System.out.println("客户端断开，channel对应的短id"+ctx.channel().id().asShortText());
		clients.remove(ctx.channel());
	}

}
