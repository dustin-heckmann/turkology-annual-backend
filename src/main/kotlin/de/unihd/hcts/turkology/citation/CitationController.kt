package de.unihd.hcts.turkology.citation

import de.unihd.hcts.turkology.citation.domain.CitationId
import de.unihd.hcts.turkology.citation.domain.CitationNumber
import de.unihd.hcts.turkology.citation.domain.KeywordCode
import de.unihd.hcts.turkology.citation.domain.Volume
import de.unihd.hcts.turkology.citation.search.*
import org.springframework.web.bind.annotation.*

private const val PATH = "/api/citations"
private val DEFAULT_SKIP = 0.asSkip()
private val DEFAULT_LIMIT = 50.asLimit()

@RestController
@RequestMapping(PATH)
class CitationController(val service: CitationService) {
    @GetMapping
    fun citations(
            @RequestParam(required = false, name = "q")
            queryString: String?,

            @RequestParam(required = false, name = "keyword")
            keywordString: String?,

            @RequestParam(required = false, name = "volume")
            volumeString: String?,

            @RequestParam(required = false, name = "number")
            numberString: String?,

            @RequestParam(required = false)
            limit: Limit?,

            @RequestParam(required = false)
            skip: Skip?

    ): ListOfCitationHits {
        return service.citations(
                CitationQuery(
                        queryString = queryString.asQueryString(),
                        keyword = keywordString.asKeywordCode(),
                        volume = volumeString.asVolume(),
                        number = numberString.asNumber()
                ),
                skip ?: DEFAULT_SKIP,
                limit ?: DEFAULT_LIMIT
        )
    }

    @GetMapping("{citationId}")
    fun citationDetails(@PathVariable(required = true) citationId: CitationId) = service.citation(citationId)
}

private fun String?.asKeywordCode(): KeywordCode? = if (!this.isNullOrEmpty()) KeywordCode(this) else null

private fun String?.asQueryString(): QueryString? = if (!this.isNullOrEmpty()) QueryString(this) else null

private fun String?.asVolume(): Volume? =
        if (!this.isNullOrEmpty()) Volume(this) else null

private fun String?.asNumber(): CitationNumber? =
        if (!this.isNullOrEmpty()) CitationNumber(this) else null

