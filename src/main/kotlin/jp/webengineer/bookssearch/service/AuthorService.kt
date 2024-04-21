package jp.webengineer.bookssearch.service

import jp.webengineer.bookssearch.db.AuthorRepository
import jp.webengineer.bookssearch.model.Author
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorService(
    @Autowired val authorRepository: AuthorRepository
) {

    fun searchAuthors(name: String?): List<Author> {
        return this.authorRepository.search(name)
    }
    fun addAuthor(author: Author): Author {
        val createdAuthor = this.authorRepository.add(author)
        return createdAuthor
    }

    fun updateAuthor(authorId: String, author: Author): Author {
        return this.authorRepository.update(author)
    }
}
