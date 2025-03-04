package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `게임에 참여할 플레이어는 1명 이상이다`() {
        val jay = Player("jay")
        val joy = Player("joy")

        assertDoesNotThrow {
            Players(listOf(joy, jay))
        }
    }

    @Test
    fun `게임에 참여할 플레이어는 중복될 수 없다`() {
        val jay1 = Player("jay")
        val jay2 = Player("jay")

        assertThrows<IllegalArgumentException> { Players(listOf(jay1, jay2)) }
    }
}
