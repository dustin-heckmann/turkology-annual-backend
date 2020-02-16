package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Journal(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Journal(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asJournal() = Journal(this)
