package com.example.demospringcache

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class SimpleBookRepository : BookRepository {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Cacheable("getDefaultBook")
    override fun getDefaultBook(): Book {
        logger.info("function getDefaultBook is called")

        simulateSlowService()

        return Book(
            isbn = "isbn-default",
            title = "title-default"
        )
    }

    @Cacheable("getByIsbn")
    override fun getByIsbn(isbn: String): Book {
        logger.info("function getByIsbn is called, isbn: $isbn")

        simulateSlowService()

        return Book(
            isbn = isbn,
            title = "title-default"
        )
    }

    @Cacheable("getByIsbnAndTitle")
    override fun getByIsbnAndTitle(isbn: String, title: String): Book {
        logger.info("function getByIsbnAndTitle is called, isbn: $isbn, title: $title")

        simulateSlowService()

        return Book(
            isbn = isbn,
            title = title
        )
    }

    private fun simulateSlowService() {
        try {
            Thread.sleep(3000L)
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }
    }
}