package blackjack.domain.carddeck

import blackjack.Shape
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.carddeck.cardnumbergenerator.CardNumberGenerator
import blackjack.domain.carddeck.shapegenerator.ShapeGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `Card로 포장하여 반환한다`() {
        val expected = Card(Shape.HEART, CardNumber.SEVEN)
        val actual = CardDeck(StubShapeGenerator(), StubCardNumberGenerator()).drawCard()
        assertThat(actual).isEqualTo(expected)
    }
}

class StubShapeGenerator : ShapeGenerator {
    override fun pickShape(): Shape = Shape.HEART
}

class StubCardNumberGenerator : CardNumberGenerator {
    override fun getCardNumber(shape: Shape): CardNumber? = CardNumber.SEVEN
}
