package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonValue
import de.unihd.hcts.turkology.citation.domain.Citation

data class CitationHit(
        @JsonAlias("_source")
        @JsonValue
        val citation: Citation
)