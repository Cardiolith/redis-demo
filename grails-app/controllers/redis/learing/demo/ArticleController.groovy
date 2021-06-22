package redis.learing.demo

import grails.converters.JSON

class ArticleController {

    def articleService

    /**
     * 新增 Article
     * @return
     */
    def create() {
        def data = request.JSON
        def result = articleService.addArticle(data)
        render result as JSON
    }

    def index() {

    }

    def get(id) {

    }

    def modify(id) {

    }

    def delete() {

    }
}
