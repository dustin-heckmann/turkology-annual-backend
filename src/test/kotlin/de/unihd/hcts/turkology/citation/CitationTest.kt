package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class CitationTest {
    @Test
    fun `deserialize Citation`() {
        val json = """{"id": "1-1", "number": 1, "volume": 1, "rawText": ""}""".trimIndent()
        expectThat(objectMapper().readValue<Citation>(json)) {
            get { id }.isEqualTo("1-1".asCitationId())
            get { volume }.isEqualTo(1.asVolume())
            get { number }.isEqualTo(1.asCitationNumber())
        }
    }

    @Test
    fun `serialize Citation`() {
        val citation = Citation(
                id = "1-1".asCitationId(),
                volume = 1.asVolume(),
                number = 1.asCitationNumber(),
                rawText = "".asRawText()
        )

        expectThat(objectMapper().writeValueAsString(citation))
                .isEqualTo("""{"id":"1-1","volume":1,"number":1,"rawText":""}""")
    }
}