package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Location(
        @JsonValue
        val value: String
) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Location(value)
    }

    override fun toString() = value

}

fun String.asLocation() = Location(this)
