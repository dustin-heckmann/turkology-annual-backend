package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Citation(
        val id: CitationId,
        val title: Title? = null,
        val volume: Volume,
        val number: CitationNumber,
        val type: CitationType? = null,
        val rawText: RawText,
        val comments: List<Comment> = emptyList(),
        val reviews: List<Review> = emptyList(),
        val authors: List<Person> = emptyList(),
        val keywords: List<Keyword> = emptyList(),
        val editors: List<Person> = emptyList(),
        val translators: List<Person> = emptyList(),
        val numberOfPages: NumberOfPages? = null,
        val numberOfVolumes: NumberOfVolumes? = null,
        val publishedIn: Publication? = null,
        val location: Location? = null,
        val material: List<Material> = emptyList(),
        val amendments: List<Amendment> = emptyList(),
        val datePublished: DatePublished? = null,
        val taReferences: List<TaReference> = listOf(),
        val series: Series? = null
) {
}

val EMPTY_CITATION = Citation(
        id = "".asCitationId(),
        title = "".asTitle(),
        volume = 0.asVolume(),
        number = 0.asCitationNumber(),
        type = CitationType.article,
        rawText = "".asRawText()
)
