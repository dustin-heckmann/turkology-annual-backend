package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Person(
        @JsonAlias("first")
        @JsonProperty("first")
        val firstName: FirstName? = null,

        @JsonAlias("middle")
        @JsonProperty("middle")
        val middleName: MiddleName? = null,

        @JsonAlias("last")
        @JsonProperty("last")
        val lastName: LastName? = null,

        val raw: RawAuthorName? = null
)
