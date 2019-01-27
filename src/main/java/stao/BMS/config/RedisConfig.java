package stao.BMS.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("#{conf['redis.port']}")
    private int port;

    @Value("${redis.pass}")
    private String pass;

    @Value("#{conf['redis.database']}")

    private int database;

    @Value("#{conf['redis.maxIdle']}")
    private int maxIdle;

    @Value("#{conf['redis.minIdle']}")
    private int minIdle;

    @Value("#{conf['redis.maxTotal']}")
    private int maxTotal;

    @Value("#{conf['redis.testOnBorrow']}")
    private boolean testOnBorrow;

    @Value("#{conf['redis.testOnReturn']}")
    private boolean testOnReturn;

    @Value("#{conf['redis.maxWaitMillis']}")
    private int maxWaitMillis;

    @Value("#{conf['redis.timeBetweenEvictionRunsMillis']}")
    private int timeBetweenEvictionRunsMillis;

    @Value("#{conf['redis.testWhileIdle']}")
    private boolean testWhileIdle;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(pass));
        redisStandaloneConfiguration.setPort(port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(minIdle);
        //当池内没有可用的连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        //------其他属性根据需要自行添加-------------
        return jedisPoolConfig;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        //单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        //设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(host);
        //设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(database);
        //设置密码
        redisStandaloneConfiguration.setPassword(RedisPassword.of(pass));
        //设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(port);
        //获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        //指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
        jpcb.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        //单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }
}
