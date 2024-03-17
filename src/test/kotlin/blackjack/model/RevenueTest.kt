package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RevenueTest {

    @Test
    fun `(딜러 lose , 플레이어 블랙잭) 정상적인 Payout과 수익금 테스트`() {
        val dealerResult = mapOf(Result.LOSE to 1)
        val player =
            Player(Wallet(Identification("누누"), 100)).apply {
                draw(Card(Denomination.ACE, Suit.CLOVER))
                draw(Card(Denomination.KING, Suit.DIAMOND))
            }
        val playerResults = mapOf(player to Result.WIN)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(150, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 lose , 플레이어 win) 정상적인 Payout과 수익금 테스트`() {
        val dealerResult = mapOf(Result.LOSE to 1)
        val playerResults = mapOf(Player(Wallet(Identification("누누"), 50)) to Result.WIN)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(50, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 블랙잭 , 플레이어 블랙잭 ) 정상적인 Payout과 수익금 테스트`() {
        val dealerResult = mapOf(Result.DRAW to 1)
        val player =
            Player(Wallet(Identification("누누"), 50)).apply {
                draw(Card(Denomination.ACE, Suit.CLOVER))
                draw(Card(Denomination.KING, Suit.DIAMOND))
            }
        val playerResults = mapOf(player to Result.DRAW)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(50, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 draw , 플레이어 draw) 정상적인 Payout과 수익금 테스트`() {
        val dealerResult = mapOf(Result.DRAW to 1)
        val playerResults = mapOf(Player(Wallet(Identification("누누"), 50)) to Result.DRAW)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(0, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 win , 플레이어 lose) 정상적인 Payout과 수익금 테스트`() {
        val dealerResult = mapOf(Result.WIN to 1)
        val playerResults = mapOf(Player(Wallet(Identification("누누"), 50)) to Result.LOSE)
        val gameResult = GameResult(dealerResult, playerResults)
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(-50, actualRevenues.first().revenue)
    }
}
