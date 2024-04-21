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
    fun add(author: Author): Author {
        val createdAuthor = this.dslContext.newRecord(AUTHOR).also {
            it.id = ULID.random()
            it.name = author.name
            it.birthDay = author.birthDay
            it.comment = author.comment
            it.store()
        }
        return createdAuthor.toAuthor()!!
    }

    fun get(authorId: String): Author? {
        val author = this.dslContext.select()
            .from(AUTHOR)
            .where(AUTHOR.ID.eq(authorId))
            .fetchOne() as AuthorRecord?

        if (author == null) return null
        return author.toAuthor()
    }

    fun update(author: Author): Author {
        this.dslContext.update(AUTHOR)
            .set(AUTHOR.NAME, author.name)
            .set(AUTHOR.BIRTH_DAY, author.birthDay)
            .set(AUTHOR.COMMENT, author.comment)
            .where(AUTHOR.ID.eq(author.id))
            .execute()
        return get(author.id!!)!!
    }

    private fun AuthorRecord?.toAuthor(): Author? {
        if (this == null) return null

        return Author(
            id = this.id,
            name = this.name ?: "",
            birthDay = this.birthDay,
            comment = this.comment
        )
    }
}
