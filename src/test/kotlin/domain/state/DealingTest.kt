package domain.state

import model.domain.card.Card
import model.domain.card.Hand
import model.domain.state.gameinprogress.Dealing
import model.domain.state.gameinprogress.Hit
import model.domain.state.gameover.BlackJack
import model.tools.CardNumber
import model.tools.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealingTest {

    @Test
    fun dealingToDealing() {
        // given
        val dealing = Dealing(Hand())
        val card = Card.of(Shape.HEARTS, CardNumber.TEN)

        // when
        val state = dealing.draw(card)

        // then
        assertThat(state).isInstanceOf(Dealing::class.java)
    }

    @Test
    fun dealingToBlackJack() {
        // given
        val aceCard = Card.of(Shape.HEARTS, CardNumber.ACE)
        val kingCard = Card.of(Shape.HEARTS, CardNumber.KING)
        val dealing = Dealing(Hand(aceCard))

        // when
        val state = dealing.draw(kingCard)

        // then
        assertThat(state).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun dealingToHit() {
        // given
        val aceCard = Card.of(Shape.HEARTS, CardNumber.ACE)
        val newCard = Card.of(Shape.HEARTS, CardNumber.SEVEN)
        val dealing = Dealing(Hand(aceCard))

        // when
        val state = dealing.draw(newCard)

        // then
        assertThat(state).isInstanceOf(Hit::class.java)
    }
}
