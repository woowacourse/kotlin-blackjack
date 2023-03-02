package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class NameTest {

    @Test
    fun `이름은 20자 이하다`() {
        assertDoesNotThrow {
            Name("아크,로피,아크,로피,아크,로피,아크")
        }
    }

    @Test
    fun `이름이 21자 이상이면 에러난다`() {
        assertThrows<IllegalArgumentException> {
            Name("가나다라마바사아자차카타파하abcdefjhijklmnopqrstuvwxyz")
        }
    }
}
