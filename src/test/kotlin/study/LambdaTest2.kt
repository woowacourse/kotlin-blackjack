import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

class LambdaTest2 {
    @Test
    fun sumAll() {
        assertThat(sumWith(numbers) { true })
            .isEqualTo(21)
    }

    @Test
    fun sumAllEven() {
        assertThat(sumWith(numbers) { it % 2 == 0 })
            .isEqualTo(12)
    }

    @Test
    fun sumAllOverThree() {
        assertThat(sumWith(numbers) { it > 3 })
            .isEqualTo(15)
    }
}

fun interface Condition {
    operator fun invoke(int: Int): Boolean
}
fun sumWith(numbers: List<Int>, condition: Condition): Int {
    var total = 0
    for (number in numbers) {
        if (condition(number)) {
            total += number
        }
    }
    return total
}
