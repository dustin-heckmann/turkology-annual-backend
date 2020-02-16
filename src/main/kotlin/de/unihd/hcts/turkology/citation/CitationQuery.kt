package de.unihd.hcts.turkology.citation

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
