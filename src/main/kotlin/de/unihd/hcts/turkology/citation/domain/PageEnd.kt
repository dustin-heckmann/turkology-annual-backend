package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class PageEnd(val value: Int) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                PageEnd(value)
    }

    @JsonValue
    override fun toString() = value.toString()
}

fun Int.asPageEnd() = PageEnd(this)
