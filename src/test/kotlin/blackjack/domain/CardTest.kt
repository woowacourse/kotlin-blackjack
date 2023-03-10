package blackjack.domain

import blackjack.Shape.HEART
import blackjack.domain.CardNumber.ACE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Shape와 Value를 저장한다`() {
        val shape = HEART
        val value = ACE
        val card = Card(shape, value)
        assertThat(card.shape).isEqualTo(HEART)
        assertThat(card.cardNumber).isEqualTo(ACE)
    }
}
