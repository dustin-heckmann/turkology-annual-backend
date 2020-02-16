package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Amendment(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                Amendment(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asAmendment() = Amendment(this)
