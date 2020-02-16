package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonValue

data class CitationHit(
        @JsonAlias("_source")
        @JsonValue
        val citation: Citation
)