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
    fun `플레이어가 A를 갖고있고 합이 21을 초과하는 경우 A 중 일부의 가치가 1이 될 수 있다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.ACE, CardShape.DIAMOND))
            receive(Card(CardNumber.ACE, CardShape.CLOVER))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }

        assertThat(player.getScore()).isEqualTo(13)
    }

    @Test
    fun `카드의 합이 21을 넘기면 true를 반환한다`() {
        val player = Player("pobi").apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.KING, CardShape.DIAMOND))
            receive(Card(CardNumber.KING, CardShape.CLOVER))
        }

        assertThat(player.isBusted()).isTrue
    }
}
