package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {
    @Test
    fun 이동() {
        val car = Car("jason", 0)
        val actual: Car = car.move { true }
        assertThat(actual).isEqualTo(Car("jason", 1))
    }

    @Test
    fun 정지() {
        val car = Car("jason", 0)
        val actual: Car = car.move { false }
        assertThat(actual).isEqualTo(Car("jason", 0))
    }

    data class Car(val name: String, val position: Int) {
        fun move(moveStrategy: MoveStrategy): Car {
            if (moveStrategy.isMovable()) {
                return copy(position = position + 1)
            }
            return this
        }
    }
    fun interface MoveStrategy {
        fun isMovable(): Boolean
    }

    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun sumAll() {
        sumWithCondition(numbers) {
            true
        }
    }
    @Test
    fun sumAllEven() {
        sumWithCondition(numbers) {
            it % 2 == 0
        }
    }
    @Test
    fun sumAllOverThree() {
        sumWithCondition(numbers) {
            it > 3
        }
    }

    fun sumWithCondition(numbers: List<Int>, condition: SumCondition): Int {
        var total = 0
        for (number in numbers) {
            if (condition.condition(number))
                total += number
        }
        return total
    }

    fun interface SumCondition {
        fun condition(number: Int): Boolean
    }
}
