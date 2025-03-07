package dsl

fun indroduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
