package blackjack.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NameTest {
    @ParameterizedTest
    @ValueSource(strings = ["아", "01234567890123456789"])
    fun `이름은 1~20자 이하다`(name: String) {
        assertDoesNotThrow {
            Name(name)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "012345678901234567890"])
    fun `이름은 1~20자 이외면 에러난다`(name: String) {
        assertThrows<IllegalArgumentException> {
            Name(name)
        }
    }
}
