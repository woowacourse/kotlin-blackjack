package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 카드 핸드 상태를 알아낸다`() {
        val dealer =
            Dealer(
                CardHand(
                    Card(CardShape.CLOVER, CardNumber.ONE),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                    Card(CardShape.SPADE, CardNumber.SIX),
                ),
            )

        assertThat(dealer.getCardHandState(true)).isEqualTo(CardHandState.HIT)
    }
}
