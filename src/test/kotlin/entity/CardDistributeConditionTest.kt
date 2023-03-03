package entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardDistributeConditionTest {
    @Test
    fun `y나 n이 입력되지 않은 경우 오류가 발생한다`() {
        val message = assertThrows<IllegalArgumentException> {
            CardDistributeCondition("k")
        }.message

        assertThat(message).isEqualTo("y나 n을 입력하여야 합니다. 입력된 값 : k")
    }

    @Test
    fun `y가 입력될 경우 오류가 발생하지 않는다`() {
        assertDoesNotThrow {
            CardDistributeCondition("y")
        }
    }

    @Test
    fun `n이 입력될 경우 오류가 발생하지 않는다`() {
        assertDoesNotThrow {
            CardDistributeCondition("n")
        }
    }
}