package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class KeywordCode(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Title(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asKeywordCode() =
        KeywordCode(this)
