package study

val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

fun sumAll(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        total += number
    }
    return total
}

fun sumAllEven(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        if (number % 2 == 0) {
            total += number
        }
    }
    return total
}

fun sum(numbers: List<Int>, rule: (Int) -> Boolean = { true }): Int {
    var total = 0
    for (number in numbers) {
        if (rule(number)) {
            total += number
        }
    }
    return total
}

fun main() {
    sum(numbers) { number -> number > 1 }
}
