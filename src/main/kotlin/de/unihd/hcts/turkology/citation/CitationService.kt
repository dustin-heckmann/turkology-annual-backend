package de.unihd.hcts.turkology.citation

import org.springframework.stereotype.Service


@Service
class CitationService(val citationApiClient: CitationApiClient) {
    fun citation(citationId: CitationId): Citation = citationApiClient.citation(citationId)
    fun citations(
            query: CitationQuery,
            skip: Skip,
            limit: Limit
    ): ListOfCitationHits {
        return citationApiClient.citations(
                query,
                skip,
                limit
        )
    }
}
