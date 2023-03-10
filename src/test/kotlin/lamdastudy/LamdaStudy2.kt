package lamdastudy

val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

fun sumAll(numbers: List<Int>): Int {
    return sumSeries { true }
}

fun sumAllEven(numbers: List<Int>): Int {
    return sumSeries { number ->
        number % 2 == 0
    }
}

fun sumAllOverThree(numbers: List<Int>): Int {
    return sumSeries { number ->
        (number > 3)
    }
}

typealias A = (Int) -> Boolean

fun sumSeries(sumStrategy: A): Int {
    var total = 0
    for (number in numbers) {
        if (sumStrategy(number)) {
            total += number
        }
    }
    return total
}

fun interface SumStrategy {
    fun how(number: Int): Boolean
}
