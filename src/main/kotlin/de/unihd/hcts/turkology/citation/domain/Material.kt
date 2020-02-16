package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Material(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Material(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asMaterial() = Material(this)
