package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultDeciderTest {

    @ParameterizedTest
    @MethodSource("makeTestPlayers")
    fun `플레이어의 점수가 21점 초과시 베팅 금액을 모두 잃는다`(player: Player) {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardRank.QUEEN, Shape.CLUB),
                    Card(CardRank.JACK, Shape.SPADE),
                )
            )
        )
        val winOrLose = GameResultDecider(
            dealer, Players(listOf(player))
        ).compareWinOrLose()

        Assertions.assertEquals(-10000f, winOrLose.playerResults[0].profit)
    }

    @Test
    fun `딜러의 처음 두 장의 카드 합이 21이 아니며 플레이어의 처음 두 장의 카드 합이 21일 경우 플레이어는 베팅 금액의 1_5배를 받는다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardRank.QUEEN, Shape.CLUB),
                    Card(CardRank.JACK, Shape.SPADE),
                )
            )
        )
        val playerCards = Cards(
            listOf(
                Card(CardRank.QUEEN, Shape.CLUB),
                Card(CardRank.ACE, Shape.SPADE),
            )
        )
        val winOrLose = GameResultDecider(
            dealer, Players(listOf(Player("jay", playerCards, 10000f)))
        ).compareWinOrLose()

        Assertions.assertEquals(15000f, winOrLose.playerResults[0].profit)

    }


    companion object {
        @JvmStatic
        private fun makeTestPlayers(): Stream<Player> = listOf(
            Player(
                "jay", Cards(
                    listOf(
                        Card(CardRank.QUEEN, Shape.CLUB),
                        Card(CardRank.JACK, Shape.SPADE),
                        Card(CardRank.FIVE, Shape.SPADE),
                    )
                ), 10000f
            ),
            Player(
                "jay", Cards(
                    listOf(
                        Card(CardRank.KING, Shape.CLUB),
                        Card(CardRank.JACK, Shape.SPADE),
                        Card(CardRank.TEN, Shape.SPADE),
                    )
                ), 10000f
            )
        ).stream()
    }
}
