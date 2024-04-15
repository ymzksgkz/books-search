package jp.webengineer.bookssearch.db

import com.github.guepardoapps.kulid.ULID
import jp.webengineer.bookssearch.jooq.tables.Author.Companion.AUTHOR
import jp.webengineer.bookssearch.jooq.tables.records.AuthorRecord
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class AuthorRepository(
    @Autowired val dslContext: DSLContext
) {
    fun add(): AuthorRecord {
        val createdAuthor = this.dslContext.newRecord(AUTHOR).also {
            it.id = ULID.random()
            it.name = "test"
            it.birthDay = LocalDate.of(1960, 1, 1)
            it.comment = "test comment"
            it.store()
        }
        return createdAuthor
    }
}
