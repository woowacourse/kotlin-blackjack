package domain.card

import model.domain.card.Card
import model.domain.card.Deck
import model.tools.CardNumber
import model.tools.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `4개의 카드로 덱을 생성하면, 크기는 4이다`() {
        // given
        val cards = listOf(
            Card.of(Shape.DIAMONDS, CardNumber.EIGHT),
            Card.of(Shape.DIAMONDS, CardNumber.SEVEN),
            Card.of(Shape.DIAMONDS, CardNumber.THREE),
            Card.of(Shape.HEARTS, CardNumber.NINE),
        )

        // when
        val actual = Deck.create(cards).cards

        // then
        assertThat(actual).hasSize(cards.size)
    }

    @Test
    fun `4장으로 구성된 덱에서, 첫 번째장 다이아8를 뽑는다`() {
        // given
        val cards = listOf(
            Card.of(Shape.DIAMONDS, CardNumber.EIGHT),
            Card.of(Shape.DIAMONDS, CardNumber.SEVEN),
            Card.of(Shape.DIAMONDS, CardNumber.THREE),
            Card.of(Shape.HEARTS, CardNumber.NINE),
        )
        val deck = Deck.create(cards)

        // when
        val actual = deck.getCard()

        // then
        assertThat(actual).isEqualTo(Card.of(Shape.DIAMONDS, CardNumber.EIGHT))
    }
}
