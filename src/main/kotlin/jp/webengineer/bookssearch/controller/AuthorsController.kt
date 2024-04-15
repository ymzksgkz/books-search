package jp.webengineer.bookssearch.controller

import jp.webengineer.bookssearch.model.Author
import jp.webengineer.bookssearch.service.AuthorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/authors")
class AuthorsController(
    @Autowired val authorService: AuthorService
) {
    @Throws(ResponseStatusException::class)

    @PostMapping
    fun addAuthor(@RequestBody author: Author): ResponseEntity<Author> {
        return try {
            val createdAuthor = this.authorService.addAuthor(author)
            ResponseEntity(createdAuthor, HttpStatus.OK)
        } catch (e: Exception) {
            logger.error("addAuthor: author: $author", e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    companion object {
        val logger = LoggerFactory.getLogger(AuthorsController::class.java)!!
    }
}