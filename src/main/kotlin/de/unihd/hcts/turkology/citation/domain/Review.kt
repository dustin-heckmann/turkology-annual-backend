package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Review(val value: String) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Review(value)
    }

    @JsonValue
    override fun toString() = value

}

fun String.asReview() = Review(this)
