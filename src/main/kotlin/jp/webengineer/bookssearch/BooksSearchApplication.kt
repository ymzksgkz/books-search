package jp.webengineer.bookssearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooksSearchApplication

fun main(args: Array<String>) {
	runApplication<BooksSearchApplication>(*args)
}
