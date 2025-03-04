package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `게임에 참여할 플레이어는 1명 이상이다`() {
        val jay = Player("jay", Cards(mutableListOf()))
        val joy = Player("joy", Cards(mutableListOf()))

        assertDoesNotThrow {
            Players(listOf(joy, jay))
        }
    }

    @Test
    fun `게임에 참여할 플레이어는 중복될 수 없다`() {
        val jay1 = Player("jay", Cards(mutableListOf()))
        val jay2 = Player("jay", Cards(mutableListOf()))

        assertThrows<IllegalArgumentException> { Players(listOf(jay1, jay2)) }
    }
}
