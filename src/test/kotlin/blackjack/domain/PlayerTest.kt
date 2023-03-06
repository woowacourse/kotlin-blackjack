package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어의 카드의 합은 가지고 있는 카드 점수의 합과 같다`() {
        val player = Player("hatti").apply {
            receive(Card(CardNumber.ACE, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.CLOVER))
        }

        assertThat(player.score).isEqualTo(21)
    }

    @Test
    fun `A가 3장이면 한 장은 11점으로, 두 장은 1점으로 계산이 된다`() {
        val player = Player("hatti").apply {
            receive(Card(CardNumber.ACE, CardShape.DIAMOND))
            receive(Card(CardNumber.ACE, CardShape.CLOVER))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }

        assertThat(player.score).isEqualTo(13)
    }

    @Test
    fun `카드의 합이 21을 넘기면 Bust 이다`() {
        val player = Player("hatti").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.CLOVER))
        }

        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `카드의 합이 21 미만이면 Hit가 가능하다`() {
        val player = Player("hatti").apply {
            receive(Card(CardNumber.TWO, CardShape.HEART))
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
        }

        assertThat(player.canHit()).isTrue
    }

    @Test
    fun `딜러와 플레이어 둘 다 21을 넘지 않는 경우 숫자가 큰 플레이어가 이긴다`() {
        val player = Player("hatti").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.HEART))
        }

        assertThat(player.against(dealer)).isEqualTo(ResultType.WIN)
    }

    @Test
    fun `플레이어가 bust된 경우 딜러와 상관없이 무조건 진다`() {
        val player = Player("hatti").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.HEART))
            receive(Card(CardNumber.FIVE, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.QUEEN, CardShape.HEART))
            receive(Card(CardNumber.FIVE, CardShape.HEART))
        }

        assertThat(player.against(dealer)).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `딜러와 플레이어 둘 다 21 이하인 경우 점수가 같으면 무승부이다`() {
        val player = Player("hatti").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
        }

        assertThat(player.against(dealer)).isEqualTo(ResultType.TIE)
    }
}
