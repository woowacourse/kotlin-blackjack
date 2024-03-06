package blackjack

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 카드 패 상태를 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
            )

        val dealer = Dealer(cardHand)

        Assertions.assertThat(dealer.getState(true)).isEqualTo(CardHandState.HIT)
    }
}
