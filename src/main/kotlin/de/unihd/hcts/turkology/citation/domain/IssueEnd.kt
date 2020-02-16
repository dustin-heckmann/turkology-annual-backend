package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class IssueEnd(val value: Int) {

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: Int) =
                IssueEnd(value)
    }

    @JsonValue
    override fun toString() = value.toString()
}

fun Int.asIssueEnd() = IssueEnd(this)
