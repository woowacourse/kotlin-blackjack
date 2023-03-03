package blackjack.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CommandTest {
    @ParameterizedTest
    @ValueSource(strings = ["y", "n", "Y", "N"])
    fun `명령어는 대소문자를 구분하지 않고 y 또는 n이다`(value: String) {
        assertDoesNotThrow {
            Command(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "b", "C", " ", ""])
    fun `명령어는 y 또는 n이 아니면 안 된다`(value: String) {
        assertThrows<IllegalArgumentException> {
            Command(value)
        }
    }
}
