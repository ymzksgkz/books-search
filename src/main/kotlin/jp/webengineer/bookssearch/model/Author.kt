package jp.webengineer.bookssearch.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class Author(
    val id: String? = null,

    val name: String,

    @JsonFormat(pattern = "yyyy-MM-dd")
    val birthDay: LocalDate? = null,

    val comment: String? = null
)