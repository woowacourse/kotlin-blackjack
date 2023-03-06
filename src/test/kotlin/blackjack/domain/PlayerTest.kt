package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
    fun `A가 3장이면 한 장은 11점으로, 두 장은 1점으로 계산이 된다`() {
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
}
