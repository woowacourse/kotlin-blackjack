package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 총합이 10이하일때 ACE의 값은 11이다`() {
        val card = Card.of(Shape.HEART, CardValue.ACE, 10)
        assertThat(card.value).isEqualTo(11)
    }

    @Test
    fun `카드 총합이 10초과일때 ACE의 값은 1이다`() {
        val card = Card.of(Shape.HEART, CardValue.ACE, 11)
        assertThat(card.value).isEqualTo(1)
    }
}
