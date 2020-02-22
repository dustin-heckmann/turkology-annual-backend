package de.unihd.hcts.turkology.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("elastic")
class ElasticSearchConfig(val host: String = "", val port: Int = 0, val index: String = "")

