package jp.webengineer.bookssearch.service

import jp.webengineer.bookssearch.db.AuthorRepository
import jp.webengineer.bookssearch.jooq.tables.records.AuthorRecord
import jp.webengineer.bookssearch.model.Author
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorService(
    @Autowired val authorRepository: AuthorRepository
) {

    fun addAuthor(author: Author): AuthorRecord {
        val createdAuthor = this.authorRepository.add(author)
        return createdAuthor
    }
}
