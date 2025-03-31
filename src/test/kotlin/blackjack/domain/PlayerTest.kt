package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름으로 구분된다`() {
        val player = Player(name = "Eden")
        assertEquals("Eden", player.name)
    }
}
