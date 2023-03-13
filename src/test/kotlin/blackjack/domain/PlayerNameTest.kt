package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayerNameTest {

    @Test
    fun `플레이어 이름 길이가 0이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            PlayerName("")
        }
    }

    @Test
    fun `플레이어 이름 길이는 1이상이어야 한다`() {
        assertDoesNotThrow {
            PlayerName("ring")
        }
    }
}
