package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BettingPayoutTest {
    @Test
    fun `게임 결과 역 전환 테스트`() {
        assertEquals(Result.WIN, Result.LOSE.reverse())
        assertEquals(Result.LOSE, Result.WIN.reverse())
        assertEquals(Result.DRAW, Result.DRAW.reverse())
    }

    @Test
    fun `(딜러 lose , 플레이어 블랙잭) 정상적인 Payout과 수익금 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.SEVEN, Suit.SPADE))
                draw(Card(Denomination.SEVEN, Suit.HEART))
            }
        val player =
            Player(Wallet(Identification("누누"), 100)).apply {
                draw(Card(Denomination.ACE, Suit.SPADE))
                draw(Card(Denomination.JACK, Suit.CLOVER))
            }

        val participants =
            Participants(
                dealer = dealer,
                players = listOf(player),
            )
        dealer.transitionToStayState()
        val gameManager = GameManager(participants = participants)
        val gameResult = gameManager.calculateGameResult()

        val expectedRevenue = 150
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(expectedRevenue, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 lose , 플레이어 win) 정상적인 Payout과 수익금 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.SEVEN, Suit.SPADE))
                draw(Card(Denomination.SEVEN, Suit.HEART))
            }
        val player =
            Player(Wallet(Identification("누누"), 50)).apply {
                draw(Card(Denomination.QUEEN, Suit.SPADE))
                draw(Card(Denomination.QUEEN, Suit.HEART))
            }

        val participants =
            Participants(
                dealer = dealer,
                players = listOf(player),
            )
        dealer.transitionToStayState()
        player.transitionToStayState()
        val gameManager = GameManager(participants = participants)
        val gameResult = gameManager.calculateGameResult()

        val expectedRevenue = 50
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(expectedRevenue, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 블랙잭 , 플레이어 블랙잭 ) 정상적인 Payout과 수익금 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.ACE, Suit.SPADE))
                draw(Card(Denomination.KING, Suit.HEART))
            }
        val player =
            Player(Wallet(Identification("누누"), 50)).apply {
                draw(Card(Denomination.ACE, Suit.CLOVER))
                draw(Card(Denomination.KING, Suit.DIAMOND))
            }

        val participants =
            Participants(
                dealer = dealer,
                players = listOf(player),
            )
        dealer.transitionToStayState()
        player.transitionToStayState()
        val gameManager = GameManager(participants = participants)
        val gameResult = gameManager.calculateGameResult()

        val expectedRevenue = 0
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(expectedRevenue, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 draw , 플레이어 draw) 정상적인 Payout과 수익금 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.SEVEN, Suit.SPADE))
                draw(Card(Denomination.SEVEN, Suit.HEART))
            }
        val player =
            Player(Wallet(Identification("누누"), 50)).apply {
                draw(Card(Denomination.SEVEN, Suit.DIAMOND))
                draw(Card(Denomination.SEVEN, Suit.CLOVER))
            }

        val participants =
            Participants(
                dealer = dealer,
                players = listOf(player),
            )
        dealer.transitionToStayState()
        player.transitionToStayState()
        val gameManager = GameManager(participants = participants)
        val gameResult = gameManager.calculateGameResult()

        val expectedRevenue = 0
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(expectedRevenue, actualRevenues.first().revenue)
    }

    @Test
    fun `(딜러 win , 플레이어 lose) 정상적인 Payout과 수익금 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.SEVEN, Suit.SPADE))
                draw(Card(Denomination.SEVEN, Suit.HEART))
            }
        val player =
            Player(Wallet(Identification("누누"), 50)).apply {
                draw(Card(Denomination.SIX, Suit.SPADE))
                draw(Card(Denomination.SIX, Suit.HEART))
            }

        val participants =
            Participants(
                dealer = dealer,
                players = listOf(player),
            )
        dealer.transitionToStayState()
        player.transitionToStayState()
        val gameManager = GameManager(participants = participants)
        val gameResult = gameManager.calculateGameResult()

        val expectedRevenue = -50
        val actualRevenues = gameResult.calculateRevenuePercentages()

        assertEquals(1, actualRevenues.size)
        assertEquals("누누", actualRevenues.first().name)
        assertEquals(expectedRevenue, actualRevenues.first().revenue)
    }
}
