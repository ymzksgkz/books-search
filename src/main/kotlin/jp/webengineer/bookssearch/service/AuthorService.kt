package jp.webengineer.bookssearch.service

import jp.webengineer.bookssearch.db.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorService(
    @Autowired val authorRepository: AuthorRepository
) {

    fun createAuthor(): String {
        this.authorRepository.add()
        return "create author"
    }
}
