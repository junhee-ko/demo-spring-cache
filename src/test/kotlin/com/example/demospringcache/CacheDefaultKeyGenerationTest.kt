package com.example.demospringcache

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.interceptor.SimpleKey

// https://docs.spring.io/spring-framework/docs/5.1.8.RELEASE/spring-framework-reference/integration.html#cache-annotations-cacheable-default-key
@SpringBootTest
class CacheDefaultKeyGenerationTest {

    @Autowired
    private lateinit var simpleBookRepository: SimpleBookRepository

    @Autowired
    private lateinit var cacheManager: CacheManager

    @Test
    fun `If only one param is given, return that instance`() {
        // given
        val isbn = "isbn-1"

        // when
        simpleBookRepository.getByIsbn(isbn)
        simpleBookRepository.getByIsbn(isbn)

        // then
        val cache = cacheManager.getCache("getByIsbn")
        val cacheKey = isbn
        val cachedValue: Cache.ValueWrapper? = cache?.get(cacheKey)
        assertNotNull(cachedValue)
    }

    @Test
    fun `If more the one param is given, return a SimpleKey that contains all parameters`() {
        // given
        val isbn = "isbn-1"
        val title = "title-jko"

        // when
        simpleBookRepository.getByIsbnAndTitle(isbn, title)
        simpleBookRepository.getByIsbnAndTitle(isbn, title)

        // then
        val cache = cacheManager.getCache("getByIsbnAndTitle")
        val cacheKey = SimpleKey(isbn, title)
        val cachedValue: Cache.ValueWrapper? = cache?.get(cacheKey)
        assertNotNull(cachedValue)
    }

    @Test
    fun `If no params are given, return SimpleKey EMPTY`() {
        // given

        // when
        simpleBookRepository.getDefaultBook()
        simpleBookRepository.getDefaultBook()

        // then
        val cache = cacheManager.getCache("getDefaultBook")
        val cacheKey = SimpleKey.EMPTY
        val cachedValue: Cache.ValueWrapper? = cache?.get(cacheKey)
        assertNotNull(cachedValue)
    }
}