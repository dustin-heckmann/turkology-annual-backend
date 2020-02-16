package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Keyword(
        val code: KeywordCode? = null,
        val nameDE: KeywordString? = null,
        val nameEN: KeywordString? = null,
        val raw: RawKeyword? = null,

        @JsonAlias("super")
        @JsonProperty("super")
        val superKeyword: Keyword? = null
)
