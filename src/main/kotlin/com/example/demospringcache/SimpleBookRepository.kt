package com.example.demospringcache

import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class SimpleBookRepository : BookRepository {

    override fun getByIsbn(isbn: String): Book {
        simulateSlowService()

        return Book(
            isbn = isbn,
            title = "jko book"
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