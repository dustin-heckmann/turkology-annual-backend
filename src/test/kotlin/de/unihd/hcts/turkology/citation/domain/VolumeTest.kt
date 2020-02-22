package de.unihd.hcts.turkology.citation.domain

import com.fasterxml.jackson.module.kotlin.readValue
import de.unihd.hcts.turkology.citation.objectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class VolumeTest {
    @Test
    fun `converts empty string to null` () {
        expectThat(Volume.create("")) {
            isEqualTo(null)
        }
    }
}