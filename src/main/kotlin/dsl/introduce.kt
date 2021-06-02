package dsl

fun introduce(function: Person.() -> Unit): Person {
    return Person().apply(function)
}
