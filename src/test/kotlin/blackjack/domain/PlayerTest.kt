package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayerTest {

    @Test
    fun `이름을 생성할 수 있다`() {
        assertDoesNotThrow { Player("수달") }
    }
}
