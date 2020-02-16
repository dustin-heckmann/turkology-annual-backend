package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class DatePublished(
        val year: Year? = null
) {
}

