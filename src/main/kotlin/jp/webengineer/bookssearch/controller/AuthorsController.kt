package jp.webengineer.bookssearch.controller

import jp.webengineer.bookssearch.jooq.tables.records.AuthorRecord
import jp.webengineer.bookssearch.model.Author
import jp.webengineer.bookssearch.service.AuthorService
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

    @PostMapping("/create")
    fun createAuthor(@RequestBody author: Author): ResponseEntity<AuthorRecord> {
        val createdAuthor = this.authorService.addAuthor(author)
        return ResponseEntity(createdAuthor, HttpStatus.OK)
    }
}