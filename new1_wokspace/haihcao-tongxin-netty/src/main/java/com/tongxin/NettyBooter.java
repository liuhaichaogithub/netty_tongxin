package com.tongxin;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tongxin.netty.WebsocketServer;


@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent()==null) {
			try {
				WebsocketServer.getInstance().start();
			} catch (Exception e) {
				System.out.println("netty启动异常！！！！");
				e.printStackTrace();
			}
		}
	}

	
	
}
