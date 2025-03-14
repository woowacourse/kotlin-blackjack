package model

import model.card.Card
import model.card.CardRank
import model.card.Cards
import model.card.Shape
import model.participant.Dealer
import model.participant.Player
import model.participant.Players
import model.result.GameResultDecider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultDeciderTest {
    private lateinit var dealer: Dealer
    private lateinit var players: Players

    private fun assertProfit(expectedProfit: Float) {
        val winOrLose = GameResultDecider(dealer, players).totalGameResult()
        assertEquals(expectedProfit, winOrLose.playerResults[0].profit)
    }

    @ParameterizedTest
    @MethodSource("makeTestPlayers")
    fun `플레이어의 점수가 21점 초과시 베팅 금액을 모두 잃는다`(player: Player) {
        dealer = Dealer(Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.JACK, Shape.SPADE))))
        players = Players(listOf(player))
        assertProfit(expectedProfit = -10000f)
    }

    @Test
    fun `딜러의 처음 두 장의 카드 합이 블랙잭이 아니며 플레이어의 처음 두 장의 카드 합이 블랙잭일 경우 플레이어는 베팅 금액의 1_5배를 수익으로 받는다`() {
        dealer = Dealer(Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.JACK, Shape.SPADE))))
        val player =
            Player("jay", Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.ACE, Shape.SPADE))), 10000f)
        players = Players(listOf(player))
        assertProfit(expectedProfit = 15000f)
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭일 때 플레이어는 베팅 금액을 돌려 받는다`() {
        dealer = Dealer(Cards(listOf(Card(CardRank.KING, Shape.CLUB), Card(CardRank.ACE, Shape.HEART))))
        val player =
            Player("jay", Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.ACE, Shape.SPADE))), 10000f)
        players = Players(listOf(player))
        assertProfit(expectedProfit = 0f)
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
        assertProfit(expectedProfit = 10000f)
    }

    companion object {
        @JvmStatic
        private fun makeTestPlayers(): Stream<Player> =
            listOf(
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
                ),
                Player(
                    "jay",
                    Cards(
                        listOf(
                            Card(CardRank.KING, Shape.CLUB),
                            Card(CardRank.JACK, Shape.SPADE),
                            Card(CardRank.TEN, Shape.SPADE),
                        ),
                    ),
                    10000f,
                ),
            ).stream()
    }
}
