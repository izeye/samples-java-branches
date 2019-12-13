package learningtest.io.netty.channel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link ChannelInboundHandlerAdapter}.
 *
 * @author Johnny Lim
 */
public class ChannelInboundHandlerAdapterTests {

	private static final int PORT = 8080;

	@Test
	@Ignore
	public void test() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new TestServerInitializer());

			b.bind(PORT).sync().channel().closeFuture().sync();
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	static class TestServerInitializer extends ChannelInitializer<SocketChannel> {

		private static final StringDecoder STRING_DECODER = new StringDecoder();
		private static final StringEncoder STRING_ENCODER = new StringEncoder();

		private static final TestServerHandler SERVER_HANDLER = new TestServerHandler();

		private static final int MAX_FRAME_LENGTH = 8192;

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ChannelPipeline pipeline = ch.pipeline();

			pipeline.addLast(new DelimiterBasedFrameDecoder(MAX_FRAME_LENGTH, Delimiters.lineDelimiter()));
			pipeline.addLast(STRING_DECODER);
			pipeline.addLast(STRING_ENCODER);
			pipeline.addLast(SERVER_HANDLER);
		}

	}

	@Sharable
	static class TestServerHandler extends SimpleChannelInboundHandler<String> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
			System.out.println("Received: " + msg);
		}

	}

}
