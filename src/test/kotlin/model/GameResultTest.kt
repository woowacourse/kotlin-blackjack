package model

import model.GameResult.Companion.compareWinOrLose
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import util.TestCards

class GameResultTest {
    @Test
    fun `딜러가 블랙잭일 때 플레이어의 승패를 결정할 수 있다`() {
        val cards =
            listOf(
                TestCards.DIAMOND_ACE,
                TestCards.DIAMOND_QUEEN,
            )

        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                TestCards.DIAMOND_TWO,
                TestCards.DIAMOND_TEN,
                TestCards.DIAMOND_EIGHT,
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput = compareWinOrLose(dealer, players)

        val playerWinOrLose = gameOutput.all { it.result == GameResult.LOSE }
        assertTrue(playerWinOrLose)
    }

    @Test
    fun `딜러가 20일 때 플레이어가 20이면 무숭부임을 결정할 수 있다`() {
        val cards =
            listOf(
                TestCards.DIAMOND_ACE,
                TestCards.DIAMOND_NINE,
            )

        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                TestCards.DIAMOND_TWO,
                TestCards.DIAMOND_TEN,
                TestCards.DIAMOND_EIGHT,
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput = compareWinOrLose(dealer, players)

        val playerWinOrLose = gameOutput.all { it.result == GameResult.PUSH }
        assertTrue(playerWinOrLose)
    }

    @Test
    fun `딜러가 18일 때 플레이어가 20이면 승임을 결정할 수 있다`() {
        val cards =
            listOf(
                TestCards.HEART_NINE,
                TestCards.DIAMOND_NINE,
            )

        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                TestCards.DIAMOND_TWO,
                TestCards.DIAMOND_TEN,
                TestCards.DIAMOND_EIGHT,
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput = compareWinOrLose(dealer, players)

        val playerWinOrLose = gameOutput.all { it.result == GameResult.WIN }
        assertTrue(playerWinOrLose)
    }

    @Test
    fun `딜러가 BUST일 때 플레이어가 20이면 숭임을 결정할 수 있다`() {
        val cards =
            listOf(
                TestCards.SPADE_FOUR,
                TestCards.HEART_NINE,
                TestCards.HEART_TEN,
            )

        val dealer = Dealer(Hand(cards))

        val playerCards =
            listOf(
                TestCards.DIAMOND_TWO,
                TestCards.DIAMOND_TEN,
                TestCards.DIAMOND_EIGHT,
            )

        val player = Player("joy", Hand(playerCards))
        val players = Players(listOf(player))
        val gameOutput: List<PlayerResult> = compareWinOrLose(dealer, players)

        val playerWinOrLose = gameOutput.all { it.result == GameResult.WIN }
        assertTrue(playerWinOrLose)
    }
}
