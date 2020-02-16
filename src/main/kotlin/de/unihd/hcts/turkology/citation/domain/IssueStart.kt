package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class IssueStart(val value: Int) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                IssueStart(value)
    }

    @JsonValue
    override fun toString() = value.toString()
}

fun Int.asIssueStart() = IssueStart(this)
