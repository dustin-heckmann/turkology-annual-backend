package de.unihd.hcts.turkology.citation.search

import de.unihd.hcts.turkology.citation.domain.CitationNumber
import de.unihd.hcts.turkology.citation.domain.KeywordCode
import de.unihd.hcts.turkology.citation.domain.Volume

data class CitationQuery(
        val queryString: QueryString? = null,
        val keyword: KeywordCode? = null,
        val volume: Volume? = null,
        val number: CitationNumber? = null
)

data class QueryString(val value: String) {
    override fun toString() = value
}

fun String.asQueryString() = QueryString(this)
