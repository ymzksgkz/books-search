package jp.webengineer.bookssearch.db

import com.github.guepardoapps.kulid.ULID
import jp.webengineer.bookssearch.jooq.tables.Author.Companion.AUTHOR
import jp.webengineer.bookssearch.jooq.tables.records.AuthorRecord
import jp.webengineer.bookssearch.model.Author
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository(
    @Autowired val dslContext: DSLContext
) {
    fun add(author: Author): AuthorRecord {
        val createdAuthor = this.dslContext.newRecord(AUTHOR).also {
            it.id = ULID.random()
            it.name = author.name
            it.birthDay = author.birthDay
            it.comment = author.comment
            it.store()
        }
        return createdAuthor
    }
}
