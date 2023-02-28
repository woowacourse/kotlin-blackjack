package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어의 카드의 합은 가지고 있는 카드 점수의 합과 같다`() {
        val player = Player("pobi")
        player.receive(Card(CardNumber.ACE, CardShape.DIAMOND))
        player.receive(Card(CardNumber.KING, CardShape.CLOVER))

        assertThat(player.getScore()).isEqualTo(21)
    }
}
