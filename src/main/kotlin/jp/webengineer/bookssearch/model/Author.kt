package jp.webengineer.bookssearch.model

import java.util.Date

data class Author(
    val id: String,
    val name: String,
    val birthDay: Date? = null,
    val remarks: String? = null
)