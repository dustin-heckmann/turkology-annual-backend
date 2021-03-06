package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Comment(val value: String) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Comment(value)
    }

    @JsonValue
    override fun toString() = value

}

fun String.asComment() = Comment(this)
