package dsl

infix fun Any.to(other: Any) = Pair(this, other)

fun main() {
    val value = 3 to "hello"
    val value1 = 4 to "bye"
    val values = listOf(value, value1)
    values.forEach { println(it.first) }
    values.forEach { println(it.second) }
}
