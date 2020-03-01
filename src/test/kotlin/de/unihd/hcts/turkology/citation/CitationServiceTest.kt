package de.unihd.hcts.turkology.citation

import de.unihd.hcts.turkology.citation.domain.asCitationId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@SpringBootTest
class CitationServiceTest {
    @Test
    fun `get citation by id`(@Autowired service: CitationService) {
        val result = service.citation("1-2".asCitationId())
        expectThat(result.isRight())
        result.map { expectThat(it) { get { id }.isEqualTo("1-2".asCitationId()) } }
    }
}
