package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class RawText(val value: String) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                RawText(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asRawText() = RawText(this)
