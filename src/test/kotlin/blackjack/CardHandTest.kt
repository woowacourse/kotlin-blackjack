package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardHandTest {
    @Test
    fun `카드 핸드의 총 합을 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ONE),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.CLOVER, CardNumber.QUEEN),
            )
        val actual = cardHand.sum()
        assertThat(actual).isEqualTo(24)
    }

    @Test
    fun `패에 카드를 한 장 더 추가한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ONE),
                Card(CardShape.HEART, CardNumber.SEVEN),
            )

        cardHand.addNewCard()
        assertThat(cardHand.hand.size).isEqualTo(3)

        println()
    }
}
