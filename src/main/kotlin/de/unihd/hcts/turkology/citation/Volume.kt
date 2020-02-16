package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Volume(@JsonValue val value: Int) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                Volume(value)
    }

    override fun toString() = value.toString()
}

fun Int.asVolume() = Volume(this)
