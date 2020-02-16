package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class MiddleName(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                MiddleName(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asMiddleName() = MiddleName(this)
