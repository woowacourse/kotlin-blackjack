package study

class NumberTest {

    fun sumAll(
        inputNumbers: List<Int>,
        condition: (number: Int) -> Boolean = {
            true
        },
    ): Int {

        var total = 0

        inputNumbers.forEach { number ->
            if (condition(number)) {
                total += number
            }
        }

        return total
    }

    fun sumAllEven(inputNumbers: List<Int>) = sumAll(inputNumbers) { number ->
        number % 2 == 0
    }

    fun sumAllOverThree(inputNumbers: List<Int>) = sumAll(inputNumbers) { number ->
        number > 3
    }
}

fun main() {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    println(NumberTest().sumAll(numbers))

    println(NumberTest().sumAllEven(numbers))

    println(NumberTest().sumAllOverThree(numbers))
}
