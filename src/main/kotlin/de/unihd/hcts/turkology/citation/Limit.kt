package de.unihd.hcts.turkology.citation

data class Limit(val value: Int) {
    constructor(value: String) : this(value.toInt())
}

fun Int.asLimit() = Limit(this)