package redis.learing.demo

import grails.gorm.MultiTenant

class Article implements MultiTenant<Article> {

    static constraints = {
    }

    Integer id
    String title
    String link
    String poster
    BigDecimal time
    Integer votes = 0
}
