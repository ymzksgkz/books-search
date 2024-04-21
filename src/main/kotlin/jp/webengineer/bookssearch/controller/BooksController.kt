package jp.webengineer.bookssearch.controller

import jp.webengineer.bookssearch.model.Book
import jp.webengineer.bookssearch.service.BookService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/books")
class BooksController(
    @Autowired val bookservice: BookService
) {
    @Throws(ResponseStatusException::class)

    @GetMapping("/author/{authorId}")
    fun searchByAuthorId(
        @PathVariable authorId: String
    ): ResponseEntity<List<Book>> {
        return try {
            val books = this.bookservice.searchByAuthorId(authorId)
            ResponseEntity(books, HttpStatus.OK)
        } catch (e: Exception) {
            logger.error("searchByAuthorId: authorId: $authorId", e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @GetMapping
    fun searchAuthors(
        @RequestParam(required = false) title: String?
    ): ResponseEntity<List<Book>> {
        return try {
            val authors = this.bookservice.searchAuthors(title)
            ResponseEntity(authors, HttpStatus.OK)
        } catch (e: Exception) {
            logger.error("searchAuthors: title: $title", e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PostMapping
    fun addAuthor(@RequestBody author: Book): ResponseEntity<Book> {
        return try {
            val createdBook = this.bookservice.addAuthor(author)
            ResponseEntity(createdBook, HttpStatus.OK)
        } catch (e: Exception) {
            logger.error("addAuthor: author: $author", e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PatchMapping("/{id}")
    fun updateAuthor(@PathVariable id: String, @RequestBody author: Book): ResponseEntity<Book> {
        return try {
            val updatedBook = this.bookservice.updateAuthor(id, author)
            ResponseEntity(updatedBook, HttpStatus.OK)
        } catch (e: Exception) {
            logger.error("addAuthor: author: $author", e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    companion object {
        val logger = LoggerFactory.getLogger(BooksController::class.java)!!
    }
}