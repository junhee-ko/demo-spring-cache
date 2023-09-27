package com.example.demospringcache

interface BookRepository {

    fun getByIsbn(isbn: String): Book

    fun getDefaultBook(): Book
}