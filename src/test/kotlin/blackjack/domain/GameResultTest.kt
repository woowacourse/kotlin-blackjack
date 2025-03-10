package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `targetScore가 otherScore보다 크면 WIN를 반환한다`() {
        val result = GameResult.from(targetScore = 21, otherScore = 20)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `targetScore가 otherScore보다 작으면 LOSE를 반환한다`() {
        val result = GameResult.from(targetScore = 18, otherScore = 19)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `targetScore와 otherScore가 같으면 PUSH를 반환한다`() {
        val result = GameResult.from(targetScore = 20, otherScore = 20)
        assertEquals(GameResult.PUSH, result)
    }
}
