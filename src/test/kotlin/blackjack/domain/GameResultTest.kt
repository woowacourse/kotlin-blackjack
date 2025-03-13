package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어가 버스트되면 딜러가 승리한다`() {
        val result = GameResult.from(dealerSum = 18, playerSum = 22, isPlayer = false)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어가 버스트되면 플레이어가 패배한다`() {
        val result = GameResult.from(dealerSum = 18, playerSum = 22, isPlayer = true)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `딜러가 버스트되고 플레이어는 버스트되지 않으면 플레이어가 승리한다`() {
        val result = GameResult.from(dealerSum = 22, playerSum = 21, isPlayer = true)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러가 버스트되지 않고 플레이어가 버스트되면 딜러가 승리한다`() {
        val result = GameResult.from(dealerSum = 21, playerSum = 22, isPlayer = false)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 높으면 플레이어가 승리한다`() {
        val result = GameResult.from(dealerSum = 20, playerSum = 21, isPlayer = true)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 낮으면 딜러가 승리한다`() {
        val result = GameResult.from(dealerSum = 21, playerSum = 15, isPlayer = false)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어와 딜러의 점수가 같으면 무승부이다`() {
        val result = GameResult.from(dealerSum = 21, playerSum = 21, isPlayer = true)
        assertEquals(GameResult.PUSH, result)
    }

    @Test
    fun `플레이어와 딜러가 모두 버스트되면 딜러가 승리한다`() {
        val result = GameResult.from(dealerSum = 22, playerSum = 22, isPlayer = false)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `딜러가 버스트되고 플레이어는 정상 점수라면 플레이어가 승리한다`() {
        val result = GameResult.from(dealerSum = 22, playerSum = 18, isPlayer = true)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어만 버스트되면 딜러가 승리한다`() {
        val result = GameResult.from(dealerSum = 18, playerSum = 22, isPlayer = true)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 크면 플레이어가 승리한다`() {
        val result = GameResult.from(dealerSum = 18, playerSum = 21, isPlayer = true)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `플레이어의 점수가 딜러보다 작으면 딜러가 승리한다`() {
        val result = GameResult.from(dealerSum = 21, playerSum = 13, isPlayer = true)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `플레이어와 딜러의 점수가 동일하면 무승부이다`() {
        val result = GameResult.from(dealerSum = 21, playerSum = 21, isPlayer = true)
        assertEquals(GameResult.PUSH, result)
    }
}
