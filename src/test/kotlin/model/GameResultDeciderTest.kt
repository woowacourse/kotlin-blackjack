package model

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameResultDeciderTest {
    @Test
    fun `딜러가 블랙잭일 때 플레이어의 승패를 결정할 수 있다`() {
        val cards =
            listOf(
                Card.of(CardRank.ACE, Shape.DIAMOND),
                Card.of(CardRank.QUEEN, Shape.DIAMOND),
            )
        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                Card.of(CardRank.TWO, Shape.DIAMOND),
                Card.of(CardRank.TEN, Shape.DIAMOND),
                Card.of(CardRank.EIGHT, Shape.DIAMOND),
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput = GameResultDecider(dealer, players).compareWinOrLose()
        val playersGameOutput: List<PlayerResult> = gameOutput.playerResults

        val playerWinOrLose = playersGameOutput.all { it.result == GameResult.LOSE }
        assertTrue(playerWinOrLose)
    }

    @Test
    fun `딜러가 20일 때 플레이어가 20이면 무숭부임을 결정할 수 있다`() {
        val cards =
            listOf(
                Card.of(CardRank.ACE, Shape.DIAMOND),
                Card.of(CardRank.NINE, Shape.DIAMOND),
            )
        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                Card.of(CardRank.TWO, Shape.DIAMOND),
                Card.of(CardRank.TEN, Shape.DIAMOND),
                Card.of(CardRank.EIGHT, Shape.DIAMOND),
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput = GameResultDecider(dealer, players).compareWinOrLose()
        val playersGameOutput: List<PlayerResult> = gameOutput.playerResults

        val playerWinOrLose = playersGameOutput.all { it.result == GameResult.PUSH }
        assertTrue(playerWinOrLose)
    }

    @Test
    fun `딜러가 18일 때 플레이어가 20이면 승임을 결정할 수 있다`() {
        val cards =
            listOf(
                Card.of(CardRank.NINE, Shape.HEART),
                Card.of(CardRank.NINE, Shape.DIAMOND),
            )
        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                Card.of(CardRank.TWO, Shape.DIAMOND),
                Card.of(CardRank.TEN, Shape.DIAMOND),
                Card.of(CardRank.EIGHT, Shape.DIAMOND),
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput = GameResultDecider(dealer, players).compareWinOrLose()
        val playersGameOutput: List<PlayerResult> = gameOutput.playerResults

        val playerWinOrLose = playersGameOutput.all { it.result == GameResult.WIN }
        assertTrue(playerWinOrLose)
    }

    @Test
    fun `딜러가 BUST일 때 플레이어가 20이면 숭임을 결정할 수 있다`() {
        val cards =
            listOf(
                Card.of(CardRank.FOUR, Shape.SPADE),
                Card.of(CardRank.NINE, Shape.HEART),
                Card.of(CardRank.TEN, Shape.HEART),
            )
        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                Card.of(CardRank.TWO, Shape.DIAMOND),
                Card.of(CardRank.TEN, Shape.DIAMOND),
                Card.of(CardRank.EIGHT, Shape.DIAMOND),
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput = GameResultDecider(dealer, players).compareWinOrLose()
        val playersGameOutput: List<PlayerResult> = gameOutput.playerResults

        val playerWinOrLose = playersGameOutput.all { it.result == GameResult.WIN }
        assertTrue(playerWinOrLose)
    }
}
