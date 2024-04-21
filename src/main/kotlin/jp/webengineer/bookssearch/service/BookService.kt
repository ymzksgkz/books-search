package jp.webengineer.bookssearch.service

import jp.webengineer.bookssearch.db.BookRepository
import jp.webengineer.bookssearch.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookService(
    @Autowired val bookRepository: BookRepository
) {

    fun searchByAuthorId(authorId: String): List<Book> {
        return this.bookRepository.searchByAuthorId(authorId)
    }

    fun searchAuthors(title: String?): List<Book> {
        return this.bookRepository.search(title)
    }
    fun addAuthor(book: Book): Book {
        val createdBook = this.bookRepository.add(book)
        return createdBook
    }

    fun updateAuthor(authorId: String, book: Book): Book {
        return this.bookRepository.update(book)
    }
}
