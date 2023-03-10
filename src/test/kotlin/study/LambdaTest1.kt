import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest1 {
    @Test
    fun `이동`() {
        val car = Car("아크", 0)
        val actual: Car = car.move { true }
        assertThat(actual).isEqualTo(Car("아크", 1))
    }

    @Test
    fun `정지`() {
        val car = Car("아크", 0)
        val actual: Car = car.move { false }
        assertThat(actual).isEqualTo(Car("아크", 0))
    }
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