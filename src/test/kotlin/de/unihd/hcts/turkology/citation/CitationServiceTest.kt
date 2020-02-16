package de.unihd.hcts.turkology.citation

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CitationServiceTest {
    @Test
    fun `get citation by id`() {
        val service = CitationService()
        val citation: Citation = service.citation(CitationId("1-2"))
        expectThat(citation) {
            get { id }.isEqualTo(CitationId("1-2"))
        }
    }
}
