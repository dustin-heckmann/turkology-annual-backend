package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class RawAuthorName(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                RawAuthorName(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asRawAuthorName() = RawAuthorName(this)
