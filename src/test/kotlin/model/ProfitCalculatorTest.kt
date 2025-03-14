package model

import model.card.Card
import model.card.CardRank
import model.card.Cards
import model.card.Shape
import model.participant.Dealer
import model.participant.Player
import model.participant.Players
import model.result.ProfitCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProfitCalculatorTest {
    private lateinit var dealer: Dealer
    private lateinit var players: Players

    private fun assertPlayerProfit(expectedProfit: Float) {
        val profitCalculator = ProfitCalculator(dealer, players)
        assertEquals(expectedProfit, profitCalculator.playerProfits[0].profit)
    }

    @Test
    fun `플레이어의 점수가 21점 초과시 베팅 금액을 모두 잃는다`() {
        dealer = Dealer(Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.JACK, Shape.SPADE))))
        val player =
            Player(
                "jay",
                Cards(
                    listOf(
                        Card(CardRank.QUEEN, Shape.CLUB),
                        Card(CardRank.JACK, Shape.SPADE),
                        Card(CardRank.FIVE, Shape.SPADE),
                    ),
                ),
                10000f,
            )
        players = Players(listOf(player))
        assertPlayerProfit(expectedProfit = -10000f)
    }

    @Test
    fun `딜러의 처음 두 장의 카드 합이 블랙잭이 아니며 플레이어의 처음 두 장의 카드 합이 블랙잭일 경우 플레이어는 베팅 금액의 1_5배를 수익으로 받는다`() {
        dealer = Dealer(Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.JACK, Shape.SPADE))))
        val player =
            Player("jay", Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.ACE, Shape.SPADE))), 10000f)
        players = Players(listOf(player))
        assertPlayerProfit(expectedProfit = 15000f)
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭일 때 플레이어는 베팅 금액을 돌려 받는다`() {
        dealer = Dealer(Cards(listOf(Card(CardRank.KING, Shape.CLUB), Card(CardRank.ACE, Shape.HEART))))
        val player =
            Player("jay", Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.ACE, Shape.SPADE))), 10000f)
        players = Players(listOf(player))
        assertPlayerProfit(expectedProfit = 0f)
    }

    @Test
    fun `딜러의 점수가 블랙잭 점수를 초과할 경우 플레이어는 베팅 금액만큼 수익을 받는다`() {
        val cards =
            Cards(
                listOf(
                    Card(CardRank.QUEEN, Shape.CLUB),
                    Card(CardRank.JACK, Shape.SPADE),
                    Card(CardRank.KING, Shape.SPADE),
                ),
            )
        val player =
            Player("jay", Cards(listOf(Card(CardRank.KING, Shape.CLUB), Card(CardRank.KING, Shape.SPADE))), 10000f)
        dealer = Dealer(cards)
        players = Players(listOf(player))
        assertPlayerProfit(expectedProfit = 10000f)
    }

    @Test
    fun `플레이어가 패배할 때 딜러의 수익은 플레이어의 베팅금액과 같다`() {
        val dealerCard =
            Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.ACE, Shape.SPADE)))
        dealer = Dealer(dealerCard)
        val player =
            Player("jay", Cards(listOf(Card(CardRank.KING, Shape.CLUB), Card(CardRank.KING, Shape.SPADE))), 10000f)
        players = Players(listOf(player))
        assertThat(ProfitCalculator(dealer, players).dealerProfit()).isEqualTo(10000f)
    }
}
