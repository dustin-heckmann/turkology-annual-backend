package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class LastName(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                LastName(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asLastName() = LastName(this)
