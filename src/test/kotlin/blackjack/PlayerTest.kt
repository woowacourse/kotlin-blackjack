package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 카드 패의 상태를 구한다`() {
        val cardHand =
            PlayerCardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
            )

        val player = Player("해나", cardHand)

        assertThat(player.getState(true)).isEqualTo(CardHandState.HIT)
    }
}
