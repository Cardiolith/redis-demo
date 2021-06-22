package redis.learing.demo

import common.RedisKeys
import grails.gorm.transactions.Transactional
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

@Transactional
class VoteService {

    static final long ONE_WEEK_IN_SECONDS = 7 * 86400
    static final double VOTE_SCORE = 432

    def redisService

    /**
     * 投票 Function
     * @param userId
     * @param articleId
     * @return
     */
    def articleVote(Integer userId, Integer articleId) {
        long cutoff = System.currentTimeMillis() / 1000 - ONE_WEEK_IN_SECONDS
        redisService.withRedis { jedis ->
            if (jedis.zscore(RedisKeys.getTimeKey(), "$articleId") < cutoff) {   // 判断当前时间是否距离文章发表时间超过7天
                return
            }
            if (jedis.sadd(RedisKeys.getVoteKey(articleId), "$userId")) {    // 如果user还未给article投票, 分数统计自增
                jedis.zincrby(RedisKeys.getScoreKey(), VOTE_SCORE, RedisKeys.getArticleKey(articleId))
                jedis.hincrBy(RedisKeys.getArticleKey(articleId), "votes", 1)
            }
        }
    }

    /**
     * when add new article，init redis Key
     * @param article
     */
    def addArticleInfo(article) {
        String key = RedisKeys.getArticleKey(article.id)
        Long currentSeconds = System.currentTimeMillis() / 1000
        redisService.withRedis { jedis ->
            jedis.hmset(key, article)
            jedis.zadd(RedisKeys.getScoreKey(), 0, key)
            jedis.zadd(RedisKeys.getTimeKey(), currentSeconds.toDouble(), "${article.id}")
        }
    }
}
