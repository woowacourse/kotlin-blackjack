package dsl

import java.lang.StringBuilder

fun <T> T.applyThenReturn1(f: (T) -> Unit): T {
    f(this)
    return this
}

fun <T> T.applyThenReturn2(f: T.() -> Unit): T {
    f()
    return this
}

fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n ALPHABET WITH!")
    toString()
}

fun alphabet2() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n ALPHABET APPLY!")
    toString()
}

fun main() {
    val aaron = "aaron".applyThenReturn1 { println(it.toUpperCase()) }
    val aaron2 = "aaron".applyThenReturn2 { println(toUpperCase()) }
    println(alphabet())
    println(alphabet2())
}