package study

fun sum(numbers: List<Int>, sumRule: (Int) -> Boolean = { true }): Int {
    var total = 0
    for (number in numbers) {
        if (sumRule(number)) {
            total += number
        }
    }
    return total
}

val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

// fun sumAll(number: Int) = true
fun sumAllEven(number: Int) = number % 2 == 0
fun sumAllOverThree(number: Int) = number > 3

fun main() {
    println(sum(numbers))
    println(sum(numbers) { sumAllEven(it) })
    println(sum(numbers) { sumAllOverThree(it) })
}
