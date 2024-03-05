package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardHandTest {
    @Test
    fun `카드 리스트를 가지고 있다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ONE),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.CLOVER, CardNumber.QUEEN),
            )

        assertThat(cardHand).isEqualTo(
            CardHand(
                listOf(
                    Card(CardShape.CLOVER, CardNumber.ONE),
                    Card(CardShape.HEART, CardNumber.SEVEN),
                    Card(CardShape.SPADE, CardNumber.SIX),
                    Card(CardShape.CLOVER, CardNumber.QUEEN),
                ),
            ),
        )
    }
}
