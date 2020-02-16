package de.unihd.hcts.turkology.citation

data class Skip(val value: Int) {
    constructor(value: String) : this(value.toInt())
}

fun Int.asSkip() = Skip(this)