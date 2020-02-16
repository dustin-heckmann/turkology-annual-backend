package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Year(@JsonValue val value: Int) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                Year(value)
    }

    override fun toString() = value.toString()
}

fun Int.asYear() = Year(this)
