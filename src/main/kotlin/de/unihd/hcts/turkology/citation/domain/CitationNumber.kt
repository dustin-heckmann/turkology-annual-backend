package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class CitationNumber(@JsonValue val value: Int) {
    constructor(value: String): this(value.toInt())

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                CitationNumber(value)
    }

    override fun toString() = value.toString()
}

fun Int.asCitationNumber() =
        CitationNumber(this)
