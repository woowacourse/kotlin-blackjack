package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Shape와 Value를 저장한다`() {
        val shape = Shape.HEART
        val value = CardNumber.ACE
        val card = Card(shape, value)
        assertThat(card.shape).isEqualTo(Shape.HEART)
        assertThat(card.cardNumber).isEqualTo(CardNumber.ACE)
    }
}
