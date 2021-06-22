package redis.learing.demo

class BootStrap {

    def redisService

    def init = { servletContext ->
        redisService.init()
    }
    def destroy = {
    }
}
