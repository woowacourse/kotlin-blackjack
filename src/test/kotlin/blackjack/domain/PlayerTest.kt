package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `플레이어가 버스트가 아니고 딜러의 점수보다 높다면 승부에서 이긴다`() {
        val player = Player("pobi").apply {
            betting(Money(10000))
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.NINE, CardShape.DIAMOND))
        }

        assertThat(player against dealer).isEqualTo(ResultType.WIN)
    }

    @Test
    fun `플레이어가 버스트가 아니고 딜러의 점수와 같다면 승부에서 비긴다`() {
        val player = Player("pobi").apply {
            betting(Money(10000))
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }

        assertThat(player against dealer).isEqualTo(ResultType.TIE)
    }

    @Test
    fun `플레이어가 버스트가 아니고 딜러가 버스트가 아닐 때 딜러의 점수보다 낮다면 승부에서 진다`() {
        val player = Player("pobi").apply {
            betting(Money(10000))
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.DIAMOND))
        }

        assertThat(player against dealer).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `딜러가 버스트라면 플레이어의 점수와 상관없이 승부에서 이긴다`() {
        val player = Player("pobi").apply {
            betting(Money(10000))
            receive(Card(CardNumber.JACK, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.SIX, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }

        assertThat(player against dealer).isEqualTo(ResultType.WIN)
    }

    @Test
    fun `딜러가 버스트가 아니고 플레이어가 버스트라면 승부에서 진다`() {
        val player = Player("pobi").apply {
            betting(Money(10000))
            receive(Card(CardNumber.JACK, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.TWO, CardShape.HEART))
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
        }

        assertThat(player against dealer).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `플레이어는 이름으로 구분된다`() {
        val player1 = Player("thomas")
        val player2 = Player("pobi")
        val player3 = Player("thomas")

        assertAll(
            { assertThat(player1).isNotEqualTo(player2) },
            { assertThat(player1).isEqualTo(player3) },
        )
    }
}
