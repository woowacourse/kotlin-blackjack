package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RevenueTest {
    @Test
    fun `딜러 lose , 플레이어 블랙잭인 상황에서 수익금은 150원이어야 한다`() {
        val dealerResult = mapOf(Result.LOSE to 1)
        val player =
            createPlayerWithCards(
                100,
                Card(Denomination.ACE, Suit.CLOVER),
                Card(Denomination.KING, Suit.DIAMOND),
            )
        val playerResults = mapOf(player to Result.WIN)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(150, actualRevenues.first().revenue)
    }

    @Test
    fun `딜러 lose , 플레이어 win인 상황에서 수익금은 100원이어야 한다`() {
        val dealerResult = mapOf(Result.LOSE to 1)
        val playerResults = mapOf(Player(Wallet(Identification("누누"), 100)) to Result.WIN)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(100, actualRevenues.first().revenue)
    }

    @Test
    fun `딜러 블랙잭, 플레이어 블랙잭인 상황에서 수익금은 0원이어야 한다`() {
        val dealerResult = mapOf(Result.DRAW to 1)
        val player =
            createPlayerWithCards(
                100,
                Card(Denomination.ACE, Suit.CLOVER),
                Card(Denomination.KING, Suit.DIAMOND),
            )
        val playerResults = mapOf(player to Result.DRAW)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(0, actualRevenues.first().revenue)
    }

    @Test
    fun `딜러 draw , 플레이어 draw인 상황에서 수익금은 0원이어야 한다`() {
        val dealerResult = mapOf(Result.DRAW to 1)
        val playerResults = mapOf(Player(Wallet(Identification("누누"), 100)) to Result.DRAW)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(0, actualRevenues.first().revenue)
    }

    @Test
    fun `딜러 win , 플레이어 lose인 상황에서 수익금은 -100원이어야 한다`() {
        val dealerResult = mapOf(Result.WIN to 1)
        val playerResults = mapOf(Player(Wallet(Identification("누누"), 100)) to Result.LOSE)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(-100, actualRevenues.first().revenue)
    }
}
