package jp.webengineer.bookssearch.db

import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository(
    @Autowired val dslContext: DSLContext
) {
    fun add() {
        // TODO implement
    }
}
