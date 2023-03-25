package domain.state

import model.domain.card.Card
import model.domain.card.Hand
import model.domain.state.gameinprogress.Hit
import model.domain.state.gameover.Bust
import model.tools.CardNumber
import model.tools.Shape
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HitTest {

    @Test
    fun hitToHit() {
        // given
        val cards = listOf(
            Card.of(Shape.DIAMONDS, CardNumber.EIGHT),
            Card.of(Shape.DIAMONDS, CardNumber.SEVEN),
        )
        val newCard = Card.of(Shape.HEARTS, CardNumber.ACE)
        val state = Hit(Hand(cards))

        // when
        val actual = state.draw(newCard)

        // then
        Assertions.assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun hitToBust() {
        // given
        val cards = listOf(
            Card.of(Shape.DIAMONDS, CardNumber.EIGHT),
            Card.of(Shape.DIAMONDS, CardNumber.SEVEN),
        )
        val newCard = Card.of(Shape.HEARTS, CardNumber.TEN)
        val state = Hit(Hand(cards))

        // when
        val actual = state.draw(newCard)

        // then
        Assertions.assertThat(actual).isInstanceOf(Bust::class.java)
    }
}
