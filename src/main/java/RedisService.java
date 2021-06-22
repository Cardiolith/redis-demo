import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.commands.JedisCommands;

import java.util.function.Consumer;

public class RedisService {

    private Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Value("${redis.host}")
    private String host;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port:6379}")
    private Integer port;

    private JedisPool jedisPool;

    public void init() {
        logger.info("===== RedisService begin init =====");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1024);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(1000L);
        int timeOut = 1000;
        jedisPool = new JedisPool(config, host, port, timeOut, password);
    }

    public void withRedis(Consumer<JedisCommands> consumer) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            consumer.accept(jedis);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
