package de.unihd.hcts.turkology.citation

import org.springframework.web.bind.annotation.*

private const val PATH = "/api/citations"
private val DEFAULT_SKIP = 0.asSkip()
private val DEFAULT_LIMIT = 50.asLimit()

@RestController
@RequestMapping(PATH)
class CitationController(val service: CitationService) {
    @GetMapping
    fun citations(
            @RequestParam(required = false)
            q: QueryString?,

            @RequestParam(required = false)
            keyword: KeywordCode?,

            @RequestParam(required = false)
            volume: Volume?,

            @RequestParam(required = false)
            number: CitationNumber?,

            @RequestParam(required = false)
            limit: Limit?,

            @RequestParam(required = false)
            skip: Skip?

    ): ListOfCitationHits {
        return service.citations(
                CitationQuery(
                        queryString = q,
                        keyword = keyword,
                        volume = volume,
                        number = number
                ),
                skip ?: DEFAULT_SKIP,
                limit ?: DEFAULT_LIMIT
        )
    }

    @GetMapping("{citationId}")
    fun citationDetails(@PathVariable(required = true) citationId: CitationId) = service.citation(citationId)
}
