package com.example.demospringcache

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class SimpleBookRepository : BookRepository {

    @Cacheable("books")
    override fun getByIsbn(isbn: String): Book {
        simulateSlowService()

        return Book(
            isbn = isbn,
            title = "jko book"
        )
    }

    @Cacheable("default-book")
    override fun getDefaultBook(): Book =
        Book(
            isbn = "isbn-default",
            title = "title-default"
        )

    private fun simulateSlowService() {
        try {
            Thread.sleep(3000L)
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }
    }
}