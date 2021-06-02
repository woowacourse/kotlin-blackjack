package dsl

fun List<String>.add(str: String): List<String> {
    return this.plus(str)
}

fun Any.bla(str: String): String {
    return this.toString() + str
}

fun main() {
    val list = listOf("1","2","3")
    println(list.add("123"))
}