package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayerTest {
    @Test
    fun `이름을 넘겨받아 플레이어를 생성한다`() {
        val name = "krrong"
        assertDoesNotThrow { Player(name) }
    }
}
