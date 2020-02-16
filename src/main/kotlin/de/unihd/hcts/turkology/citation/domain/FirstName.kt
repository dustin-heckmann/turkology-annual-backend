package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class FirstName(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                FirstName(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asFirstName() = FirstName(this)
