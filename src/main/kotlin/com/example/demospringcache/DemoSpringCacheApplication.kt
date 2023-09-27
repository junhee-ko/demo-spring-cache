package com.example.demospringcache

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

/*
* The @EnableCaching annotation triggers a post-processor
* that inspects every Spring bean for the presence of caching annotations on public methods.
* If such an annotation is found,
* a proxy is automatically created to intercept the method call
* and handle the caching behavior accordingly.
* */
@SpringBootApplication
@EnableCaching
class DemoSpringCacheApplication

fun main(args: Array<String>) {
	runApplication<DemoSpringCacheApplication>(*args)
}
