package com.example.demospringcache

interface BookRepository {

    fun getDefaultBook(): Book

    fun getByIsbn(isbn: String): Book

    fun getByIsbnAndTitle(isbn: String, title: String): Book

}