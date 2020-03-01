package de.unihd.hcts.turkology.citation

import de.unihd.hcts.turkology.citation.domain.CitationId
import de.unihd.hcts.turkology.citation.search.CitationQuery
import de.unihd.hcts.turkology.citation.search.Limit
import de.unihd.hcts.turkology.citation.search.Skip
import org.springframework.stereotype.Service


@Service
class CitationService(val citationApiClient: CitationApiClient) {
    fun citation(citationId: CitationId) = citationApiClient.citation(citationId)

    fun citations(query: CitationQuery, skip: Skip, limit: Limit) =
            citationApiClient.citations(query, skip, limit)
}
