### Redis Demo

#### 使用 redis 对文章进行投票

- 使用 hash 存储文章信息 (article::id)

- 使用 zset 存储文章的发布时间 (time::)

- 使用 zset 存储文章的评分 (score::)

- 使用 set 存储每一个文章的投票者ID (voted::articleId)

 

