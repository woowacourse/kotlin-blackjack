package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {
    @Test
    fun `플레이어의 카드의 합은 가지고 있는 카드 점수의 합과 같다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.ACE, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.CLOVER))
        }

        assertThat(player.getScore()).isEqualTo(21)
    }

    @Test
    fun `플레이어가 A를 갖고있고 합이 21을 초과하는 경우 A 중 일부의 가치가 1이 될 수 있다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.ACE, CardShape.DIAMOND))
            receive(Card(CardNumber.ACE, CardShape.CLOVER))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }

        assertThat(player.getScore()).isEqualTo(13)
    }

    @Test
    fun `카드의 합이 21을 넘기면 Bust 이다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.CLOVER))
        }

        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `카드의 합이 21 미만이면 Hit가 가능하다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.TWO, CardShape.HEART))
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
        }

        assertThat(player.canHit()).isTrue
    }

    @Test
    fun `플레이어가 버스트가 아니고 딜러의 점수보다 높다면 승부에서 이긴다`() {
        val player = Player("pobi").apply {
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
            receive(Card(CardNumber.JACK, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }

        assertThat(player against dealer).isEqualTo(ResultType.WIN)
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
