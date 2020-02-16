package de.unihd.hcts.turkology.citation.search

import com.fasterxml.jackson.annotation.JsonProperty

data class CitationSearchRequest(
        @JsonProperty("_source")
        val fields: List<String>? = null
) {
}