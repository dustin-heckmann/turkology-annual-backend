package de.unihd.hcts.turkology.citation

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThan

class CitationApiClientTest() {
    private val client = CitationApiClient()

    @Test
    fun `get citation details`() {
        val citation = client.citation(CitationId("1-9"))


        expectThat(citation) {
            get { id }.isEqualTo("1-9".asCitationId())
            get { volume }.isEqualTo(1.asVolume())
            get { number }.isEqualTo(9.asCitationNumber())
            get { type }.isEqualTo(CitationType.article)
            get { title }.isEqualTo("Le dixième anniversaire de la fondation de l'Insitut d'études balkaniques".asTitle())
            get { rawText }.isEqualTo("9. Todorov, Ν. Le dixième anniversaire de la fondation de l'Insitut d'études balkaniques. In: EBalk 1974.2-3.221-224. [Institut in Sofia, Bulgarien.]".asRawText())
            get { comments }.isEqualTo(listOf("Institut in Sofia, Bulgarien".asComment()))
            get { reviews }.isEqualTo(listOf<Review>())
            get { authors }.isEqualTo(
                    listOf(
                            Person(
                                    "Ν.".asFirstName(),
                                    null,
                                    "Todorov".asLastName(),
                                    "Todorov, Ν.".asRawAuthorName()
                            )
                    )
            )
            get { editors }.isEqualTo(emptyList<Person>())
            get { translators }.isEqualTo(emptyList<Person>())
            get { keywords }.isEqualTo(
                    listOf(
                            Keyword(
                                    code = "AB".asKeywordCode(),
                                    nameDE = "Forschungsbetrieb".asKeywordString(),
                                    nameEN = "Research activities".asKeywordString(),
                                    raw = "AB. FORSCHUNGSBETRIEB".asRawKeyword(),
                                    superKeyword = Keyword(
                                            code = "A".asKeywordCode(),
                                            nameDE = "Allgemeines".asKeywordString(),
                                            nameEN = "General".asKeywordString()
                                    )
                            )
                    )
            )
            get { numberOfPages }.isEqualTo(null)
            get { numberOfVolumes }.isEqualTo(null)
            get { publishedIn }.isEqualTo(
                    Publication(
                            journal = "EBalk".asJournal(),
                            year = 1974.asYear(),
                            issueStart = 2.asIssueStart(),
                            issueEnd = 3.asIssueEnd(),
                            pageStart = 221.asPageStart(),
                            pageEnd = 224.asPageEnd(),
                            type = PublicationType.journal,
                            rawText = "EBalk 1974.2-3.221-224".asRawPublication()
                    )
            )
            get { location }.isEqualTo(null)
            get { material }.isEqualTo(emptyList<Material>())
            get { amendments }.isEqualTo(emptyList<Amendment>())
            get { datePublished }.isEqualTo(null)
            get { taReferences }.isEmpty()
            get { series }.isEqualTo(null)


        }
    }

    @Test
    fun `search citations`() {
        val searchResponse = client.citations(queryString, pagination)
        expectThat(searchResponse) {
            get { total }.isGreaterThan(0L)
        }
    }
}

