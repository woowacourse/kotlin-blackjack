package domain.card

import model.domain.card.Card
import model.tools.CardNumber
import model.tools.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardTest {

    @Test
    fun `카드 한장을 뽑는다`() {
        // given
        val shape = Shape.DIAMONDS
        val cardNumber = CardNumber.EIGHT

        // when
        val actual = Card.of(shape, cardNumber)

        // then
        assertAll(
            { assertThat(actual.cardNumber).isEqualTo(cardNumber) },
            { assertThat(actual.shape).isEqualTo(shape) },
        )
    }
}
