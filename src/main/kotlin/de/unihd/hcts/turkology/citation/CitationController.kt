package de.unihd.hcts.turkology.citation

import arrow.core.Either.Left
import arrow.core.Either.Right
import de.unihd.hcts.turkology.citation.domain.CitationId
import de.unihd.hcts.turkology.citation.domain.CitationNumber
import de.unihd.hcts.turkology.citation.domain.KeywordCode
import de.unihd.hcts.turkology.citation.domain.Volume
import de.unihd.hcts.turkology.citation.search.*
import org.springframework.http.CacheControl
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit


private const val PATH = "/api/citations"
private val DEFAULT_SKIP = 0.asSkip()
private val DEFAULT_LIMIT = 50.asLimit()
private val MAX_CACHE_AGE = 30L

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

    ) = when (val result = service.citations(
            CitationQuery(
                    queryString = queryString.asQueryString(),
                    keyword = keywordString.asKeywordCode(),
                    volume = volumeString.asVolume(),
                    number = numberString.asNumber()
            ),
            skip ?: DEFAULT_SKIP,
            limit ?: DEFAULT_LIMIT
    )) {
        is Right -> result.b.toCacheableResponse()
        is Left -> result.a.toErrorResponse()
    }

    @GetMapping("{citationId}")
    fun citationDetails(
            @PathVariable(required = true)
            citationId: CitationId
    ) = when (val result = service.citation(citationId)) {
        is Right -> result.b.toCacheableResponse()
        is Left -> result.a.toErrorResponse()
    }
}


private fun String?.asKeywordCode(): KeywordCode? = if (!this.isNullOrEmpty()) KeywordCode(this) else null

private fun String?.asQueryString(): QueryString? = if (!this.isNullOrEmpty()) QueryString(this) else null

private fun String?.asVolume(): Volume? =
        if (!this.isNullOrEmpty()) Volume(this) else null

private fun String?.asNumber(): CitationNumber? =
        if (!this.isNullOrEmpty()) CitationNumber(this) else null

private fun <T> T.toCacheableResponse(): ResponseEntity<T> {
    return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(MAX_CACHE_AGE, TimeUnit.MINUTES))
            .body<T>(this)
}

private fun Exception.toErrorResponse(): ResponseEntity<Map<String, Any?>> {
    val statusCode = when (this) {
        is IndexBadRequest -> BAD_REQUEST
        is CitationNotFound -> NOT_FOUND
        is JsonDecodeError -> BAD_GATEWAY
        is JsonMappingError -> BAD_GATEWAY
        is IndexIOError -> BAD_GATEWAY
        is IndexNotFound -> BAD_GATEWAY
        else -> INTERNAL_SERVER_ERROR
    }
    this.printStackTrace()
    return ResponseEntity.status(statusCode).body(mapOf(
            "message" to message,
            "statusReason" to statusCode.reasonPhrase,
            "statusCode" to statusCode.value()
    ))
}
