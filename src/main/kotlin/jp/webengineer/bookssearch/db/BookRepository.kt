package jp.webengineer.bookssearch.db

import com.github.guepardoapps.kulid.ULID
import jp.webengineer.bookssearch.jooq.tables.Book.Companion.BOOK
import jp.webengineer.bookssearch.jooq.tables.records.BookRecord
import jp.webengineer.bookssearch.model.Book
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class BookRepository(
    @Autowired val dslContext: DSLContext
) {

    fun search(name: String?): List<Book> {
        val books = this.dslContext.select()
            .from(BOOK)
            .where(BOOK.TITLE.like("%$name%"))
            .fetch() as List<BookRecord>

        return books.map { it.toBook()!! }
    }

    fun add(book: Book): Book {
        val createdBook = this.dslContext.newRecord(BOOK).also {
            it.id = ULID.random()
            it.title = book.title
            it.overview = book.overview
            it.authorId = book.authorId
            it.store()
        }
        return createdBook.toBook()!!
    }

    fun searchByAuthorId(authorId: String): List<Book> {
        val books = this.dslContext.select()
            .from(BOOK)
            .where(BOOK.AUTHOR_ID.eq(authorId))
            .fetch() as List<BookRecord>

        return books.map { it.toBook()!! }
    }

    fun get(bookId: String): Book? {
        val book = this.dslContext.select()
            .from(BOOK)
            .where(BOOK.ID.eq(bookId))
            .fetchOne() as BookRecord?

        if (book == null) return null
        return book.toBook()
    }

    fun update(book: Book): Book {
        this.dslContext.update(BOOK)
            .set(BOOK.TITLE, book.title)
            .set(BOOK.OVERVIEW, book.overview)
            .where(BOOK.ID.eq(book.id))
            .execute()
        return get(book.id!!)!!
    }

    private fun BookRecord?.toBook(): Book? {
        if (this == null) return null

        return Book(
            id = this.id!!,
            title = this.title ?: "",
            overview = this.overview ?: "",
            authorId = this.authorId!!
        )
    }
}
