package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import de.unihd.hcts.turkology.citation.domain.CitationNumber
import de.unihd.hcts.turkology.citation.domain.Volume

@JsonIgnoreProperties(ignoreUnknown = true)
data class TaReference(
        val number: CitationNumber,
        val volume: Volume
) {
}

