package learningtest.netty.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * Echo client.
 *
 * This has been copied from https://netty.io/4.1/xref/io/netty/example/echo/EchoClient.html.
 *
 * @author Johnny Lim
 */
public final class EchoClient {

	static final boolean SSL = System.getProperty("ssl") != null;
	static final String HOST = System.getProperty("host", "127.0.0.1");
	static final int POST = Integer.parseInt(System.getProperty("port", "8007"));
	static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

	public static void main(String[] args) throws Exception {
		SslContext sslContext;
		if (SSL) {
			sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		}
		else {
			sslContext = null;
		}

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) {
							ChannelPipeline pipeline = ch.pipeline();
							if (sslContext != null) {
								pipeline.addLast(sslContext.newHandler(ch.alloc(), HOST, POST));
							}
							pipeline.addLast(new LoggingHandler(LogLevel.INFO));
							pipeline.addLast(new EchoClientHandler());
						}
					});

			ChannelFuture future = bootstrap.connect(HOST, POST).sync();

			future.channel().closeFuture().sync();
		}
		finally {
			group.shutdownGracefully();
		}
	}

}
