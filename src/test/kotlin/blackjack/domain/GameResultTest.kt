package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어가 딜러를 이긴 경우 WIN을 반환한다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.KING, CardShape.CLOVER))
            receive(Card(CardNumber.ACE, CardShape.CLOVER))
        }
        val dealer = Player("딜러").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.HEART))
        }
        val gameResult = GameResult.of(dealer, listOf(player))

        val actual = gameResult[player]

        assertThat(actual).isEqualTo(ResultType.WIN)
    }

    @Test
    fun `플레이어가 bust 하는 경우 딜러와 상관없이 무조건 LOSE를 반환한다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.KING, CardShape.CLOVER))
            receive(Card(CardNumber.QUEEN, CardShape.CLOVER))
            receive(Card(CardNumber.TWO, CardShape.CLOVER))
        }
        val dealer = Player("딜러").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.HEART))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val gameResult = GameResult.of(dealer, listOf(player))

        val actual = gameResult[player]

        assertThat(actual).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `딜러만 bust 하는 경우 WIN을 반환한다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.KING, CardShape.CLOVER))
            receive(Card(CardNumber.TWO, CardShape.CLOVER))
        }
        val dealer = Player("딜러").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.HEART))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val gameResult = GameResult.of(dealer, listOf(player))

        val actual = gameResult[player]

        assertThat(actual).isEqualTo(ResultType.WIN)
    }

    @Test
    fun `플레이어의 승패 여부로부터 딜러의 승패 여부를 계산한다`() {
        val playerResult = mapOf(
            Player("pobi") to ResultType.WIN,
            Player("thomas") to ResultType.TIE,
            Player("hatti") to ResultType.LOSE,
            Player("jason") to ResultType.LOSE
        )

        val actual = GameResult(playerResult).dealer
        val expect = mapOf(
            ResultType.WIN to 2,
            ResultType.TIE to 1,
            ResultType.LOSE to 1
        )

        assertThat(actual).isEqualTo(expect)
    }
}
