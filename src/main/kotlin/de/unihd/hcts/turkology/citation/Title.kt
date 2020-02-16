package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Title(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Title(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asTitle() = Title(this)
