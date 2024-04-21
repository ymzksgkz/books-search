package jp.webengineer.bookssearch.model

data class Book(
    val id: String? = null,

    val title: String,

    val overview: String,

    val authorId: String
)