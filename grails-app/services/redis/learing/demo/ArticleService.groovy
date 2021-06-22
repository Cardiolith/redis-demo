package redis.learing.demo

import grails.gorm.transactions.Transactional

@Transactional
class ArticleService {

    def voteService

    /**
     * add new Article and set cache
     * @param data
     * @return
     */
    def addArticle(data) {
        Article article = new Article()
        article.properties = data
        article.save(flush: true)
        voteService.addArticleInfo(article)

        return article
    }

}
