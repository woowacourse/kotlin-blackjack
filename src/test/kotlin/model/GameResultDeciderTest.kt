package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultDeciderTest {
    private lateinit var dealer: Dealer
    private lateinit var players: Players

    @BeforeEach
    fun setDealer() {
        dealer = Dealer(Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.JACK, Shape.SPADE))))
    }

    private fun assertProfit(expectedProfit: Float) {
        val winOrLose = GameResultDecider(dealer, players).compareWinOrLose()
        assertEquals(expectedProfit, winOrLose.playerResults[0].profit)
    }

    @ParameterizedTest
    @MethodSource("makeTestPlayers")
    fun `플레이어의 점수가 21점 초과시 베팅 금액을 모두 잃는다`(player: Player) {
        players = Players(listOf(player))
        assertProfit(expectedProfit = -10000f)
    }

    @Test
    fun `딜러의 처음 두 장의 카드 합이 21이 아니며 플레이어의 처음 두 장의 카드 합이 21일 경우 플레이어는 베팅 금액의 1_5배를 받는다`() {
        players =
            Players(
                listOf(
                    Player(
                        "jay",
                        Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.ACE, Shape.SPADE))),
                        10000f
                    ),
                ),
            )
        assertProfit(expectedProfit = 15000f)
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭일 때 플레이어는 베팅 금액을 받는다`() {
        dealer = Dealer(Cards(listOf(Card(CardRank.KING, Shape.CLUB), Card(CardRank.ACE, Shape.HEART))))
        players =
            Players(
                listOf(
                    Player(
                        "jay",
                        Cards(listOf(Card(CardRank.QUEEN, Shape.CLUB), Card(CardRank.ACE, Shape.SPADE))),
                        10000f
                    ),
                ),
            )
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
