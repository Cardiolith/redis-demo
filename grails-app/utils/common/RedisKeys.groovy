package common

class RedisKeys {

    String getArticleKey(Integer id) {
        return "article::${id}"
    }

    String getTimeKey() {
        return "time::"
    }

    String getScoreKey() {
        return "score::"
    }

    String getVoteKey(Integer id) {
        return "voted::${id}"
    }
}
