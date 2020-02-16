package de.unihd.hcts.turkology.citation.search

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import de.unihd.hcts.turkology.citation.CitationHit

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListOfCitationHits(
        @JsonAlias("hits")
        val result: List<CitationHit>,
        val total: Long
)
