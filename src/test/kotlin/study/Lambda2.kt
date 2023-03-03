package study

val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

fun sum(numbers: List<Int>, fn: (Int) -> Boolean): Int {
    var total = 0
    for (number in numbers) {
        if (fn(number)) {
            total += number
        }
    }
    return total
}

fun main() {
    println(sum(numbers) { true })
    println(sum(numbers) { it % 2 == 0 })
    println(sum(numbers) { it > 3 })
}
