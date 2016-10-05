package seo.dale.practice.spring.session;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * @author Dale Seo
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

	@Bean
	public JedisConnectionFactory connectionFactory(EmbeddedRedisServerBean redisServer) throws IOException {
		System.out.println("Embedded Reids Server is loaded. : " + redisServer);
		return new JedisConnectionFactory();
	}

	@Bean
	public EmbeddedRedisServerBean redisServer() {
		return new EmbeddedRedisServerBean();
	}

	class EmbeddedRedisServerBean implements InitializingBean, DisposableBean {

		private RedisServer redisServer;

		@Override
		public void afterPropertiesSet() throws Exception {
			redisServer = new RedisServer(Protocol.DEFAULT_PORT);
			redisServer.start();
		}

		@Override
		public void destroy() throws Exception {
			if (redisServer != null) {
				redisServer.stop();
			}
		}
	}

}