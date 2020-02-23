package de.unihd.hcts.turkology.citation

import de.unihd.hcts.turkology.citation.domain.Citation
import de.unihd.hcts.turkology.citation.domain.CitationId
import de.unihd.hcts.turkology.citation.search.CitationQuery
import de.unihd.hcts.turkology.citation.search.Limit
import de.unihd.hcts.turkology.citation.search.ListOfCitationHits
import de.unihd.hcts.turkology.citation.search.Skip
import org.springframework.stereotype.Service


@Service
class CitationService(val citationApiClient: CitationApiClient) {
    fun citation(citationId: CitationId): Citation = citationApiClient.citation(citationId)
    fun citations(
            query: CitationQuery,
            skip: Skip,
            limit: Limit
    ): ListOfCitationHits = citationApiClient.citations(
            query,
            skip,
            limit
    )
}
