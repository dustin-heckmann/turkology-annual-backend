package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class PageStart(val value: Int) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                PageStart(value)
    }

    @JsonValue
    override fun toString() = value.toString()
}

fun Int.asPageStart() = PageStart(this)
