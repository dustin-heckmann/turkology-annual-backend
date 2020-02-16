package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListOfCitationHits(
        @JsonAlias("hits")
        val result: List<CitationHit>,
        val total: Long
)
