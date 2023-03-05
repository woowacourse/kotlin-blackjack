package blackjack.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NameTest {
    @ParameterizedTest
    @ValueSource(strings = ["아", "아크,로피,아크,로피,아크,로피,아크"])
    fun `이름은 1~20자 이하다`(name: String) {
        assertDoesNotThrow {
            Name(name)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "가나다라마바사아자차카타파하abcdefjhijklmnopqrstuvwxyz"])
    fun `이름은 1~20자 이외면 에러난다`(name: String) {
        assertThrows<IllegalArgumentException> {
            Name(name)
        }
    }
}
