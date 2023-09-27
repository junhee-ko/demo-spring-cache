package com.example.demospringcache

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Component

@Component
class AppRunner(
    private val bookRepository: BookRepository,
    private val cacheManager: CacheManager
): CommandLineRunner {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun run(vararg args: String?) {
        logger.info(".... Fetching books")

        logger.info("isbn-1234 --> is in cache: ${isValueInCache("isbn-1234")}")
        logger.info("isbn-1234 --> " + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-1234 --> is in cache: ${isValueInCache("isbn-1234")}")

        logger.info("isbn-4567 --> is in cache: ${isValueInCache("isbn-4567")}")
        logger.info("isbn-4567 --> " + bookRepository.getByIsbn("isbn-4567"))
        logger.info("isbn-4567 --> is in cache: ${isValueInCache("isbn-4567")}")

        logger.info("isbn-1234 --> " + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-4567 --> " + bookRepository.getByIsbn("isbn-4567"))
        logger.info("isbn-1234 --> " + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-1234 --> " + bookRepository.getByIsbn("isbn-1234"))
    }

    /*
    * Default Key Generation: If only one param is given, return that instance.
    * https://docs.spring.io/spring-framework/docs/5.1.8.RELEASE/spring-framework-reference/integration.html#cache-annotations-cacheable-default-key
    * */
    fun isValueInCache(isbn: String): Boolean {
        val cache = cacheManager.getCache("books")
        val cacheKey = isbn
        val cachedValue: Cache.ValueWrapper? = cache?.get(cacheKey)

        return cachedValue != null
    }
}