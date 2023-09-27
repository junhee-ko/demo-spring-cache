package com.example.demospringcache

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.stereotype.Component

@Component
class AppRunnerWithNoParameter(
    private val bookRepository: BookRepository,
    private val cacheManager: CacheManager
): CommandLineRunner {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun run(vararg args: String?) {
        logger.info(".... Fetching default book")

        logger.info("isbn-default --> is in cache: ${isValueInCache()}")
        logger.info("isbn-default --> " + bookRepository.getDefaultBook())
        logger.info("isbn-default --> is in cache: ${isValueInCache()}")
    }

    /*
    * Default Key Generation: If no params are given, return SimpleKey.EMPTY
    * https://docs.spring.io/spring-framework/docs/5.1.8.RELEASE/spring-framework-reference/integration.html#cache-annotations-cacheable-default-key
    * */
    private fun isValueInCache(): Boolean {
        val cache = cacheManager.getCache("default-book")
        val cacheKey = SimpleKey.EMPTY
        val cachedValue: Cache.ValueWrapper? = cache?.get(cacheKey)

        return cachedValue != null
    }
}