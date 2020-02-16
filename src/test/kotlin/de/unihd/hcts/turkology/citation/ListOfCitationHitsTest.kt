package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.module.kotlin.readValue
import de.unihd.hcts.turkology.citation.search.ListOfCitationHits
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


internal class ListOfCitationHitsTest {

    @Test
    fun `deserialize list of citations`() {
        val json =
                """{"total": 1, "hits": [{"_source": {"id":"1-1", "volume":1, "number": 1, "rawText": ""}}]}""".trimIndent()
        expectThat(objectMapper().readValue<ListOfCitationHits>(json)) {
            get { total }.isEqualTo(1)

        }
    }
}