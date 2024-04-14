package jp.webengineer.bookssearch.controller

import jp.webengineer.bookssearch.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")
class AuthorsController(
    @Autowired val authorService: AuthorService
) {

    @PostMapping("/create")
    fun createAuthor(): ResponseEntity<String> {
        val res = this.authorService.createAuthor()
        return ResponseEntity(res, HttpStatus.OK)
    }

}