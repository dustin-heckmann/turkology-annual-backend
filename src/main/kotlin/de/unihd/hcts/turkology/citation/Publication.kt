package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Publication(
        val journal: Journal? = null,
        val year: Year? = null,
        val issueStart: IssueStart? = null,
        val issueEnd: IssueEnd? = null,
        val pageStart: PageStart? = null,
        val pageEnd: PageEnd? = null,
        val type: PublicationType,
        @JsonAlias("raw")
        val rawText: RawPublication
) {
}

