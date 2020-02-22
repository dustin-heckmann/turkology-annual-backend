package de.unihd.hcts.turkology

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("de.unihd.hcts.turkology")
class TurkologyApplication

fun main(args: Array<String>) {
    runApplication<TurkologyApplication>(*args)
}
