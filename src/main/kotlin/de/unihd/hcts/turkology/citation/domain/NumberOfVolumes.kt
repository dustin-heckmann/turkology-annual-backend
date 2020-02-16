package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class NumberOfVolumes(val value: String) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String) =
                NumberOfVolumes(value)
    }

    @JsonValue
    override fun toString() = value
}

fun String.asNumberOfVolumes() = NumberOfVolumes(this)
