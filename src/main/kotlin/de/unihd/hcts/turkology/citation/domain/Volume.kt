package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Volume(@JsonValue val value: Int?) {
    constructor(value: String): this(if(value.isNotEmpty()) value.toInt() else null)


    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                Volume(value)

        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                if (value.isNotEmpty()) Volume(value.toInt()) else null


    }

    override fun toString() = value.toString()
}

fun Int.asVolume() = Volume(this)
