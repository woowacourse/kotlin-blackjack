package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어의 카드 핸드 상태를 알아낸다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ONE),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.CLOVER, CardNumber.QUEEN),
            )
        val player = Player("해나", cardHand)
        val cardHandState = player.getCardHandState(false)

        assertThat(cardHandState).isEqualTo(CardHandState.BURST)
    }
}
